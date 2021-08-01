package cn.edu.zjut.service;

import cn.edu.zjut.entity.User;

/**
 * @author zett0n
 * @date 2021/7/7 23:32
 */
public interface UserService {
    void register(User user);

    User login(User user);
}
