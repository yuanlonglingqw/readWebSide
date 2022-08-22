package org.example;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.sql.DataSource;

@SpringBootApplication
@MapperScan("org.example.dao")
public class SpringBootEntrance {
    public static void main(String[] args) {
        SpringApplication.run(SpringBootEntrance.class,args);
    }
}
