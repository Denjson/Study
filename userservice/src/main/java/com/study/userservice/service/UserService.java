package com.study.userservice.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.apache.commons.collections4.IterableUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.study.userservice.dto.UserDto;
import com.study.userservice.entity.User;
import com.study.userservice.repository.UserRepository;

public class UserService implements UserServiceInterface {

  private final UserRepository userRepository;

  @Autowired
  public UserService(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  public UserDto saveOne(User u) {
    userRepository.save(u);
    System.out.println("Saved User: " + u);
    return convertToDto(u);
  }

  public List<UserDto> saveMany(List<User> users) {
    userRepository.saveAll(users);
    System.out.println("Saved Users: " + users);
    return convertToDTOs(users);
  }

  public UserDto getById(Long id) {
    Optional<User> userOptional = userRepository.findById(id);
    User user;
    if (userOptional.isPresent()) {
      user = userOptional.get();
      System.out.println("User found: " + user.toString());
    } else {
      System.out.println("User with ID " + id + " not found.");
      return null;
    }
    return convertToDto(user);
  }

  public UserDto deleteById(Long id) {
    Optional<User> userOptional = userRepository.findById(id);
    User user;
    if (userOptional.isPresent()) {
      user = userOptional.get();
      System.out.println("User found: " + user.toString());
      userRepository.deleteById(id);
    } else {
      System.out.println("User with ID " + id + " not found.");
      return null;
    }
    return convertToDto(user);
  }

  public List<UserDto> getByIds(Set<Long> ids) {
    List<User> users = userRepository.findByIdIn(ids).get();
    System.out.println("Users found: " + users.toString());
    return convertToDTOs(users);
  }

  public UserDto getByEmail(String email) {
    Optional<User> userOptional = userRepository.getByEmail(email);
    User user;
    if (userOptional.isPresent()) {
      user = userOptional.get();
      System.out.println("User found: " + user.toString());
    } else {
      System.out.println("User with ID " + email + " not found.");
      return null;
    }
    return convertToDto(user);
  }

  public UserDto updateUser(Long id, User userDetails) {
    Optional<User> userOptional = userRepository.findById(id);
    User user;
    if (userOptional.isPresent()) {
      user = userOptional.get();
      user.setName(userDetails.getName());
      user.setSurname(userDetails.getSurname());
      user.setDate(userDetails.getDate());
      user.setEmail(userDetails.getEmail());
      userRepository.save(user);
      System.out.println("User updated: " + user.toString());
    } else {
      System.out.println("User with ID " + userDetails + " not found.");
      return null;
    }
    return convertToDto(user);
  }

  public List<UserDto> getAllUsers() {
    List<User> users = IterableUtils.toList(userRepository.findAll());
    System.out.println("All users from UserService: " + users.toString());
    List<UserDto> usersDto = this.convertToDTOs(users);
    return usersDto;
  }

  private UserDto convertToDto(User user) {
    return new UserDto(
        user.getId(), user.getName(), user.getSurname(), user.getDate(), user.getEmail());
  }

  private List<UserDto> convertToDTOs(List<User> users) {
    return users.stream().map(this::convertToDto).collect(Collectors.toList());
  }

  public User getUserLast() {
    Optional<User> u = userRepository.findTopByOrderByIdDesc();
    System.out.println("Last id is " + u.get().getId() + u);
    return u.get();
  }

  public User delUserLast() {
    if (userRepository.count() > 0) {
      Optional<User> u = userRepository.findTopByOrderByIdDesc();
      System.out.println("Delete last record: " + userRepository.findTopByOrderByIdDesc().get());
      userRepository.delete(u.get());
      return u.get();
    }
    return null;
  }

  public Iterable<User> addTestUser() {
    System.out.println("Quantity of people: " + userRepository.count());
    User u = new User();
    u.setName("X CODE");
    u.setSurname("MAN CODE");
    u.setDate(LocalDateTime.now());
    u.setEmail((int) (Math.random() * 10000) + "@me.com");
    userRepository.save(u);
    return userRepository.findAll();
  }

  public User getRandomUser() {
    List<User> entityList = new ArrayList<>();
    userRepository.findAll().forEach(entityList::add);
    Collections.shuffle(entityList);
    User u = entityList.getFirst();
    System.out.println("From UserService - Random user: " + u.toString());
    return u;
  }

  public List<UserDto> getRangeIds(Integer n) {
    List<User> users = userRepository.findIdsNative(n);
    System.out.println("All users within range: " + users.toString());
    List<UserDto> usersDto = this.convertToDTOs(users);
    return usersDto;
  }

  public List<UserDto> findByJPQL(String lastname) {
    List<User> users = userRepository.findByLastNameJPQL(lastname);
    System.out.println("All users with name <" + lastname + "> found: " + users.toString());
    List<UserDto> usersDto = this.convertToDTOs(users);
    return usersDto;
  }
}
