package cn.chinotan.service;

import cn.chinotan.entity.User;

import java.util.List;

public interface UserService {
    /**
     * 创建用户
     * @param user
     */
    public User createUser(User user);

    public User updateUser(User user);

    public void deleteUser(Long userId);

    /**
     * 修改密码
     * @param userId
     * @param newPassword
     */
    public void changePassword(Long userId, String newPassword);

    User findOne(Long userId);

    List<User> findAll();

    /**
     * 根据用户名查找用户
     * @param username
     * @return
     */
    public User findByUsername(String username);

    /**
     * 验证登录
     * @param username 用户名
     * @param password 密码
     * @param salt 盐
     * @param encryptpwd 加密后的密码
     * @return
     */
    boolean checkUser(String username, String password, String salt, String encryptpwd);
}
