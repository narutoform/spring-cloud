package cn.chinotan.dao;

import cn.chinotan.entity.User;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends ElasticsearchRepository<User, Long>{
    
    List<User> findByNameLike(String name);
    
    List<User> findByAgeIn(List<Integer> list);
    
}
