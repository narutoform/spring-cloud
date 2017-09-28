package cn.chinotan.service.impl;

import cn.chinotan.entity.User;
import cn.chinotan.service.UserService;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.index.IndexRequestBuilder;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchType;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.common.xcontent.XContentFactory;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private TransportClient transportClient;

    /**
     * http://192.168.217.135:9200/yui/user/_mappings
     * {
     * "properties": {
     * "name": {
     * "type": "text"
     * },
     * "age": {
     * "type": "integer"
     * },
     * "balance": {
     * "type": "double"
     * }
     * }
     * }
     *
     * @param user
     * @return
     */
    @Override
    public ResponseEntity saveUser(User user) {
        IndexRequestBuilder indexRequestBuilder = transportClient.prepareIndex("yui", "user");

        XContentBuilder builder = null;
        try {
            builder = new XContentFactory().jsonBuilder()
                    .startObject()
                    .field("name", user.getName())
                    .field("age", user.getAge())
                    .field("balance", user.getBalance())
                    .endObject();
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }

        IndexResponse indexResponse = indexRequestBuilder.setSource(builder).get();

        return new ResponseEntity(indexResponse.getId(), HttpStatus.OK);
    }

    /**
     * @param page
     * @param num
     * @param searchString
     * @return
     */
    @Override
    public List<Map<String, Object>> searchName(Integer page, Integer num, String searchString) {
        BoolQueryBuilder content = QueryBuilders.boolQuery().should(QueryBuilders.matchQuery("name", searchString));

        SearchHits searchHits = transportClient.prepareSearch("yui")
                .setTypes("user")
                .setQuery(content)
                .setSearchType(SearchType.QUERY_THEN_FETCH)
                .setFrom(page)
                .setSize(num)
                .get()
                .getHits();

        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
        for (SearchHit hit : searchHits) {
            Map<String, Object> source = hit.getSource();
            list.add(source);
        }

        return list;
    }

    @Override
    public ResponseEntity updataUser(User user) throws ExecutionException, InterruptedException {
        UpdateRequest request = new UpdateRequest("yui", "user", user.getId().toString());

        XContentBuilder xContentBuilder = null;
        try {
            xContentBuilder = XContentFactory.jsonBuilder().startObject();
            if (!StringUtils.isEmpty(user.getName())) {
                xContentBuilder.field("name", user.getName());
            }
            if (!StringUtils.isEmpty(user.getAge())) {
                xContentBuilder.field("age", user.getAge());
            }
            if (!StringUtils.isEmpty(user.getBalance())) {
                xContentBuilder.field("balance", user.getBalance());
            }
            xContentBuilder.endObject();
        } catch (IOException e) {
            e.printStackTrace();
        }

        request.doc(xContentBuilder);
        UpdateResponse updateResponse = transportClient.update(request).get();

        return new ResponseEntity(updateResponse.getResult().toString(), HttpStatus.OK);
    }

    // DeleteResponse[index=yui,type=user,id=AV6ALtlvE59yFjIPAqvQ,version=2,result=deleted,shards=ShardInfo{total=2, successful=1, failures=[]}]
    @Override
    public ResponseEntity deleteUser(String id) throws Exception {
        DeleteResponse deleteResponse = transportClient.prepareDelete("yui", "user", id.toString()).get();

        return new ResponseEntity(deleteResponse.getResult().toString(), HttpStatus.OK);
    }


}
