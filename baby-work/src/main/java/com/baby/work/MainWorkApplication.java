package com.baby.work;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.ComponentScan;

/**
 * @Author: Adorez
 * @Date: 2020/2/11 14:04
 * @Description:
 */
@SpringBootApplication
@EnableEurekaClient
@MapperScan("com.baby.work.mapper")
@ComponentScan(basePackages = {"com.baby.work","com.baby.common"})
public class MainWorkApplication {
    public static void main(String[] args) {
        SpringApplication.run(MainWorkApplication.class,args);
    }
}
