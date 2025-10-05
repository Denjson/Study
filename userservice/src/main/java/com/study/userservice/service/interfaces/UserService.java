package com.study.userservice.service.interfaces;

import java.util.List;
import java.util.Set;

import com.study.userservice.dto.UserDto;

public interface UserService {

  public UserDto saveOne(UserDto u);

  public List<UserDto> saveMany(List<UserDto> u);

  public UserDto getById(Long id);

  public List<UserDto> getByIds(Set<Long> ids);

  public UserDto getByEmail(String email);

  public UserDto updateUser(Long id, UserDto userDto);

  public UserDto deleteById(Long id);

  public List<UserDto> getAllUsers();

  public UserDto getUserLast();

  public UserDto delUserLast();

  public List<UserDto> addTestUser();

  public UserDto getRandomUser();

  public List<UserDto> getRangeIds(Integer n);

  public List<UserDto> findByJPQL(String lastname);
}
