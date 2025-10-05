package com.study.userservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@ComponentScan(basePackages = "com.study.userservice")
@EnableJpaRepositories
@SpringBootApplication
public class UserserviceApplication {

  public static void main(String[] args) {
    SpringApplication.run(UserserviceApplication.class, args);
    System.out.println("Running!");
  }
}
