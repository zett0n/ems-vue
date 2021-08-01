package cn.edu.zjut.entity;

import java.io.Serializable;

import lombok.Data;

/**
 * @author zett0n
 * @date 2021/7/10 22:36
 */
@Data
public class Emp implements Serializable {
    private Integer id;
    private String name;
    private String path;
    private Double salary;
    private Integer age;
}
