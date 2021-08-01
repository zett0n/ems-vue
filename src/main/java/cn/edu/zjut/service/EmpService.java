package cn.edu.zjut.service;

import java.util.List;

import cn.edu.zjut.entity.Emp;

/**
 * @author zett0n
 * @date 2021/7/10 22:39
 */
public interface EmpService {
    List<Emp> findAll();

    void save(Emp emp);

    void delete(Integer id);

    Emp findOne(Integer id);

    void update(Emp emp);
}
