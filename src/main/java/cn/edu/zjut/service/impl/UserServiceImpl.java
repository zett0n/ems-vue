package cn.edu.zjut.service.impl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import cn.edu.zjut.dao.UserDAO;
import cn.edu.zjut.entity.User;
import cn.edu.zjut.service.UserService;
import lombok.extern.slf4j.Slf4j;

/**
 * @author zett0n
 * @date 2021/7/7 23:33
 */
@Service
@Transactional
@Slf4j
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDAO userDAO;

    @Override
    public void register(User user) {
        // 判断用户是否已存在
        if (null == this.userDAO.findByUserName(user.getUsername())) {
            user.setStatus("已激活");
            user.setRegistrationTime(new Date());
            this.userDAO.save(user);

            // int i = 1 / 0;
            // log.debug("user：[{}]", user.toString());
        } else {
            throw new RuntimeException("用户名已存在！");
        }
    }

    @Override
    public User login(User user) {
        // 先根据用户查询对应的用户
        User userDB = this.userDAO.findByUserName(user.getUsername());
        // 等价于 userDB != null
        if (!ObjectUtils.isEmpty(userDB)) {
            // 判断密码
            if (userDB.getPassword().equals(user.getPassword())) {
                return userDB;
            } else {
                throw new RuntimeException("密码不正确！");
            }
        } else {
            throw new RuntimeException("用户名不存在！");
        }
    }
}
