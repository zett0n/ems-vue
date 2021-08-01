package cn.edu.zjut.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import cn.edu.zjut.dao.EmpDAO;
import cn.edu.zjut.entity.Emp;
import cn.edu.zjut.service.EmpService;

/**
 * @author zett0n
 * @date 2021/7/10 22:40
 */
@Service
@Transactional
public class EmpServiceImpl implements EmpService {
    @Autowired
    private EmpDAO empDAO;

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public List<Emp> findAll() {
        return this.empDAO.findAll();
    }

    @Override
    public void save(Emp emp) {
        this.empDAO.save(emp);
    }

    @Override
    public void delete(Integer id) {
        this.empDAO.delete(id);
    }

    @Override
    public Emp findOne(Integer id) {
        return this.empDAO.findOne(id);
    }

    @Override
    public void update(Emp emp) {
        this.empDAO.update(emp);
    }
}
