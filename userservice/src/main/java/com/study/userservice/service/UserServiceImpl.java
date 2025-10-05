package com.study.userservice.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.apache.commons.collections4.IterableUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;

import com.study.userservice.dto.UserDto;
import com.study.userservice.entity.User;
import com.study.userservice.exceptions.IdNotFoundException;
import com.study.userservice.mappers.UserMapper;
import com.study.userservice.repository.UserRepository;
import com.study.userservice.service.interfaces.UserService;

public class UserServiceImpl implements UserService {
  private final UserRepository userRepository;
  private final UserMapper userMapper;

  @Autowired
  public UserServiceImpl(UserRepository userRepository, UserMapper userMapper) {
    this.userRepository = userRepository;
    this.userMapper = userMapper;
  }

  @CacheEvict(value = "users", key = "0")
  public UserDto saveOne(UserDto userDto) {
    User user = userRepository.save(userMapper.toEntity(userDto));
    System.out.println("Saved User: " + userDto);
    return userMapper.toDTO(user);
  }

  public List<UserDto> saveMany(List<UserDto> userDTOs) {
    List<User> manyUsers = userRepository.saveAll(userMapper.manyToEntity(userDTOs));
    System.out.println("Saved Users: " + userDTOs);
    return userMapper.toDTOs(manyUsers);
  }

  @Cacheable(value = "users", key = "#id")
  public UserDto getById(Long id) {
    User user =
        userRepository
            .findById(id)
            .orElseThrow(() -> new IdNotFoundException("User not found with id: " + id));
    return userMapper.toDTO(user);
  }

  @Caching(
      evict = {@CacheEvict(value = "users", key = "#id"), @CacheEvict(value = "users", key = "0")})
  public UserDto deleteById(Long id) {
    User user =
        userRepository
            .findById(id)
            .orElseThrow(() -> new IdNotFoundException("User not found with id: " + id));
    userRepository.deleteById(id);
    return userMapper.toDTO(user);
  }

  public List<UserDto> getByIds(Set<Long> ids) {
    List<User> users = userRepository.findByIdIn(ids).get();
    if (users.isEmpty()) {
      throw new IdNotFoundException("Users not found with ids: " + ids);
    }
    System.out.println("Users found: " + users.toString());
    return userMapper.toDTOs(users);
  }

  public UserDto getByEmail(String email) {
    User user =
        userRepository
            .getByEmail(email)
            .orElseThrow(() -> new IdNotFoundException("Email not found: " + email));
    return userMapper.toDTO(user);
  }

  @CachePut(value = "users", key = "#id")
  @CacheEvict(value = "users", key = "0")
  public UserDto updateUser(Long id, UserDto userDto) {
    User user =
        userRepository
            .findById(id)
            .orElseThrow(() -> new IdNotFoundException("User not found with id: " + id));
    userDto.setId(id);
    user = userMapper.toEntity(userDto);
    userRepository.save(user);
    System.out.println("User updated: " + user.toString());
    return userMapper.toDTO(user);
  }

  @Cacheable(value = "users", key = "0")
  public List<UserDto> getAllUsers() {
    List<User> users = IterableUtils.toList(userRepository.findAll());
    System.out.println("All users from UserService: " + users.toString());
    List<UserDto> usersDto = userMapper.toDTOs(users);
    return usersDto;
  }

  public UserDto getUserLast() {
    Optional<User> u = userRepository.findTopByOrderByIdDesc();
    System.out.println("Last id is " + u.get().getId() + u);
    User user = u.get();
    UserDto userDto = userMapper.toDTO(user);
    return userDto;
  }

  @CacheEvict(value = "users", key = "0")
  public UserDto delUserLast() {
    if (userRepository.count() > 0) {
      User user = userRepository.findTopByOrderByIdDesc().get();
      System.out.println("Delete last record: " + user);
      UserDto userDto = userMapper.toDTO(user);
      userRepository.delete(user);
      return userDto;
    }
    return null;
  }

  @CacheEvict(value = "users", key = "0")
  public List<UserDto> addTestUser() {
    System.out.println("Quantity of people: " + userRepository.count());
    User u = new User();
    u.setName("X CODE");
    u.setSurname("MAN CODE");
    u.setDate(LocalDateTime.now());
    u.setEmail((int) (Math.random() * 10000) + "@me.com");
    userRepository.save(u);
    List<User> users = userRepository.findAll();
    return userMapper.toDTOs(users);
  }

  public UserDto getRandomUser() {
    List<User> entityList = new ArrayList<>();
    userRepository.findAll().forEach(entityList::add);
    Collections.shuffle(entityList);
    User user = entityList.getFirst();
    System.out.println("From UserService - Random user: " + user.toString());
    return userMapper.toDTO(user);
  }

  public List<UserDto> getRangeIds(Integer n) {
    List<User> users = userRepository.findIdsNative(n);
    System.out.println("All users within range: " + users.toString());
    List<UserDto> usersDto = userMapper.toDTOs(users);
    return usersDto;
  }

  public List<UserDto> findByJPQL(String lastname) {
    List<User> users = userRepository.findByLastNameJPQL(lastname);
    System.out.println("All users with name <" + lastname + "> found: " + users.toString());
    List<UserDto> usersDto = userMapper.toDTOs(users);
    return usersDto;
  }
}
