package cn.edu.zjut.entity;

import java.util.Date;

import lombok.Data;

/**
 * @author zett0n
 * @date 2021/7/7 23:17
 */
@Data
public class User {
    private Integer id;
    private String username;
    private String realname;
    private String password;
    private String sex;
    private String status;
    private Date registrationTime;
}
