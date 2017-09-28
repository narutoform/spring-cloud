package cn.chinotan.service;

import cn.chinotan.entity.User;
import org.springframework.http.ResponseEntity;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

public interface UserService {

    /**
     * 增加用户信息
     * @return 用户id
     */
    ResponseEntity saveUser(User user) throws IOException;

    /**
     * 根据关键词，function score query 权重分分页查询
     * @param page
     * @param num
     * @param SearchString
     * @return
     */
    List<Map<String, Object>> searchName(Integer page, Integer num, String SearchString);
    
    ResponseEntity updataUser(User user) throws ExecutionException, InterruptedException;
    
    ResponseEntity deleteUser(String id) throws Exception;
}
