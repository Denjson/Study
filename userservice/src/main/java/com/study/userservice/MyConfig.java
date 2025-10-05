package com.study.userservice;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.study.userservice.mappers.UserMapper;
import com.study.userservice.repository.UserRepository;
import com.study.userservice.service.UserServiceImpl;

@Configuration
public class MyConfig {

  @Bean
  public UserServiceImpl userService(UserRepository userRepository, UserMapper userMapper) {
    return new UserServiceImpl(userRepository, userMapper);
  }

  //  @Bean
  //  public DataSource dataSource() throws PropertyVetoException {
  //    DriverManagerDataSource dataSource = new DriverManagerDataSource();
  //    dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
  //    dataSource.setUrl("jdbc:mysql://localhost:3306/my_db?useSSL=false&serverTimezone=UTC");
  //    dataSource.setUsername("bestuser");
  //    dataSource.setPassword("bestuser");
  //    return dataSource;
  //  }
}
