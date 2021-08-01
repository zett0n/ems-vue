package cn.edu.zjut;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("cn.edu.zjut.dao")
public class EmsVueApplication {

    public static void main(String[] args) {
        SpringApplication.run(EmsVueApplication.class, args);
    }

}
