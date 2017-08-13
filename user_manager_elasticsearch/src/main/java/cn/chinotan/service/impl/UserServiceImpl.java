package cn.chinotan.service.impl;

import cn.chinotan.dao.UserRepository;
import cn.chinotan.entity.User;
import cn.chinotan.service.UserService;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.functionscore.FunctionScoreQueryBuilder;
import org.elasticsearch.index.query.functionscore.ScoreFunctionBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.highlight.HighlightBuilder;
import org.elasticsearch.search.highlight.HighlightField;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.SearchResultMapper;
import org.springframework.data.elasticsearch.core.aggregation.AggregatedPage;
import org.springframework.data.elasticsearch.core.aggregation.impl.AggregatedPageImpl;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.SearchQuery;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    private ElasticsearchTemplate elasticsearchTemplate;

    @Override
    public Long saveUser(User user) {
        User userReturn = userRepository.save(user);

        return userReturn.getId();
    }

    /**
     * {
     * "query": {
     * "match": {
     * "name": "chino"
     * }
     * },
     * "highlight": {
     * "pre_tags": "<h2>",
     * "post_tags": "</h2>",
     * "fields": {
     * "content": {}
     * }
     * }
     * }
     *
     * @param page
     * @param num
     * @param searchString
     * @return
     */
    @Override
    public List<User> searchCity(Integer page, Integer num, String searchString) {
        Pageable pageable = new PageRequest(page - 1, num);

        /*FunctionScoreQueryBuilder queryBuilder = QueryBuilders.functionScoreQuery()
                .add(QueryBuilders.boolQuery().should(QueryBuilders.matchQuery("name", searchString)),
                        ScoreFunctionBuilders.weightFactorFunction(100));*/

        //   - 字段对应权重分设置，可以优化成 enum
        //   - 由于无相关性的分值默认为 1 ，设置权重分最小值为 10
        FunctionScoreQueryBuilder queryBuilder = QueryBuilders.functionScoreQuery()
                .add(QueryBuilders.matchPhraseQuery("name", searchString),
                        ScoreFunctionBuilders.weightFactorFunction(1000))
                /*.add(QueryBuilders.matchPhraseQuery("age", searchString),
                        ScoreFunctionBuilders.weightFactorFunction(500))*/
                .scoreMode("sum") // 这个可以不加，Can be first, avg, max, sum, min, multiply
                .setMinScore(10);// 必须加这一行，不叫就全查出来了，比如上面那个queryBuilder
                
        /*FunctionScoreQueryBuilder queryBuilder = QueryBuilders.functionScoreQuery(QueryBuilders.boolQuery()
                .should(QueryBuilders.matchQuery("name", searchString)));*/

        SearchQuery sq = new NativeSearchQueryBuilder()
                .withPageable(pageable)
                .withQuery(queryBuilder)
                .build();

        Page<User> userPage = userRepository.search(sq);

        return userPage.getContent();
    }

    /**
     * 高亮查询
     *
     * @param page
     * @param num
     * @param searchString
     * @return
     */
    @Override
    public List<User> searchCityHighlight(Integer page, Integer num, String searchString) {
        Pageable pageable = new PageRequest(page - 1, num);

        FunctionScoreQueryBuilder queryBuilder = QueryBuilders.functionScoreQuery(QueryBuilders.boolQuery()
                .should(QueryBuilders.matchQuery("name", searchString)));

        HighlightBuilder.Field field = new HighlightBuilder.Field("name")
                .preTags("<font color=\"red\">")
                .postTags("</font>");

        SearchQuery sq = new NativeSearchQueryBuilder()
                .withPageable(pageable)
                .withHighlightFields(field)
                .withQuery(queryBuilder)
                .build();

        // 设置高亮
        AggregatedPage<User> users = elasticsearchTemplate.queryForPage(sq, User.class, new SearchResultMapper() {
            @Override
            public <T> AggregatedPage<T> mapResults(SearchResponse response, Class<T> clazz, Pageable pageable) {
                SearchHits hits = response.getHits();

                // 判断是否查询到了值
                if (hits.totalHits() <= 0) {
                    return null;
                }

                // 新定的要替换的list
                List<T> list = new ArrayList<T>();

                for (SearchHit hit : hits) {
                    Map<String, HighlightField> highlightFields = hit.getHighlightFields();
                    // 判断是否存在高亮域
                    if (highlightFields == null || highlightFields.isEmpty()) {
                        return new AggregatedPageImpl<T>(list);
                    }

                    String name = highlightFields.get("name").fragments()[0].toString();

                    // 取出每个查询到的map对象
                    Map<String, Object> source = hit.getSource();
                    // 用高亮显示的值代替原来的值
                    source.put("name", name);
                    // 将替换后的新值放入到新的list中， 并强制转化
                    list.add((T) source);
                }

                // 采用AggregatedPageImpl进行包装
                AggregatedPageImpl<T> ts = new AggregatedPageImpl<T>(list);

                return ts;
            }
        });

        return users.getContent();
    }

    @Override
    public List<User> findByNameLike(String name) {
        return userRepository.findByNameLike(name);
    }

    @Override
    public List<User> findByAgeIn(List<Integer> list) {
        return userRepository.findByAgeIn(list);
    }

}
