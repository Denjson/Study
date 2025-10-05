package com.study.userservice;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.study.userservice.repository.UserRepository;
import com.study.userservice.service.UserService;

@Configuration
// @ComponentScan(basePackages = {"com.study.us"})
public class MyConfig {

  @Bean
  public UserService userService(UserRepository userRepository) {
    return new UserService(userRepository);
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
