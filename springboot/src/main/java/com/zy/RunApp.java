package com.zy;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

/**
 * @author Zy
 * @date 2020/3/23 21:21
 */

/**
 * @SpringBootApplication：标注一个主程序类，用来标明这是一个Spring Boot应用
 */
@SpringBootApplication
//自动更新时间
@EnableJpaAuditing
//@ComponentScan("com.zy")
public class RunApp {
    // Spring应用启动起来
    public static void main(String[] args) {
        SpringApplication.run(RunApp.class, args);
        System.out.println("服务启动");
    }



}