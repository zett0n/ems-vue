package cn.edu.zjut;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import cn.edu.zjut.dao.EmpDAO;

/**
 * @author zett0n
 * @date 2021/7/10 22:55
 */
public class EmpDAOTest extends BasicTest {
    private static final Logger log = LoggerFactory.getLogger(EmpDAOTest.class);
    @Autowired
    private EmpDAO empDAO;

    @Test
    public void testFindAll() {
        this.empDAO.findAll().forEach(emp -> log.info("emp: {}", emp));
    }
}
