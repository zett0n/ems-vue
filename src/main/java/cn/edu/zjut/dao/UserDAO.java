package cn.edu.zjut.dao;

import org.springframework.stereotype.Repository;

import cn.edu.zjut.entity.User;

/**
 * @author zett0n
 * @date 2021/7/7 23:22
 */
@Repository
public interface UserDAO {
    void save(User user);

    User findByUserName(String username);
}
