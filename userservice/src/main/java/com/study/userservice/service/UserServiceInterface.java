package com.study.userservice.service;

import java.util.List;
import java.util.Set;

import com.study.userservice.dto.UserDto;
import com.study.userservice.entity.User;

public interface UserServiceInterface {

  public UserDto saveOne(User u);

  public List<UserDto> saveMany(List<User> u);

  public UserDto getById(Long id);

  public List<UserDto> getByIds(Set<Long> ids);

  public UserDto getByEmail(String email);

  public UserDto updateUser(Long id, User userDetails);

  public UserDto deleteById(Long id);

  public List<UserDto> getAllUsers();

  public User getUserLast();

  public User delUserLast();

  public Iterable<User> addTestUser();
}
