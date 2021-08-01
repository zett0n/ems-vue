package cn.edu.zjut;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import cn.edu.zjut.service.EmpService;

/**
 * @author zett0n
 * @date 2021/7/10 23:11
 */
public class EmpServiceTest extends BasicTest {
    private static final Logger log = LoggerFactory.getLogger(EmpServiceTest.class);
    @Autowired
    private EmpService empService;

    @Test
    public void testEmpService() {
        this.empService.findAll().forEach(emp -> log.info("emp: {}", emp));
    }
}
