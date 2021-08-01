package cn.edu.zjut.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import cn.edu.zjut.entity.Emp;

/**
 * @author zett0n
 * @date 2021/7/10 22:37
 */
@Repository
public interface EmpDAO {
    List<Emp> findAll();

    void save(Emp emp);

    void delete(Integer id);

    Emp findOne(Integer id);

    void update(Emp emp);
}
