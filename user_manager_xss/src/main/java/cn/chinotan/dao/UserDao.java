package cn.chinotan.dao;

import cn.chinotan.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/*@Repository*//*不加也行*/
public interface UserDao extends JpaRepository<User, Long> {
    
}
