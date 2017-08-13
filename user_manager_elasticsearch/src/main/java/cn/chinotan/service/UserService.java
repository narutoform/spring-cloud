package cn.chinotan.service;

import cn.chinotan.entity.User;

import java.util.List;

public interface UserService {

    /**
     * 增加用户信息
     * @return 用户id
     */
    Long saveUser(User user);

    /**
     * 根据关键词，function score query 权重分分页查询
     * @param page
     * @param num
     * @param SearchString
     * @return
     */
    List<User> searchCity(Integer page, Integer num, String SearchString);

    /**
     * 和jpa一样的查询
     * @param name
     * @return
     */
    List<User> findByNameLike(String name);

    List<User> findByAgeIn(List<Integer> list);

    List<User> searchCityHighlight(Integer page, Integer num, String searchString);
}
