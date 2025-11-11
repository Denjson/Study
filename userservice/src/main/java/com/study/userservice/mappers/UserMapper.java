package com.study.userservice.mappers;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.study.userservice.dto.UserDto;
import com.study.userservice.entity.User;

@Component
public class UserMapper {

  public UserDto toDTO(User user) {
    if (user == null) {
      return null;
    }
    return new UserDto(
        user.getId(), user.getName(), user.getSurname(), user.getDate(), user.getEmail());
  }

  public List<UserDto> toDTOs(List<User> users) {
    return users.stream().map(this::toDTO).collect(Collectors.toList());
  }

  public User toEntity(UserDto userDto) {
    if (userDto == null) {
      return null;
    }
    User user = new User();
    user.setId(userDto.getId());
    user.setName(userDto.getName());
    user.setSurname(userDto.getSurname());
    user.setDate(userDto.getDate());
    user.setEmail(userDto.getEmail());
    return user;
  }

  public List<User> manyToEntity(List<UserDto> userDTOs) {
    return userDTOs.stream().map(this::toEntity).collect(Collectors.toList());
  }
}
