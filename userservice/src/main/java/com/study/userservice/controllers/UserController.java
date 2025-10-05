package com.study.userservice.controllers;

import java.util.List;
import java.util.Set;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.study.userservice.dto.UserDto;
import com.study.userservice.service.interfaces.UserService;

@RestController
@RequestMapping(path = "/api/v1")
public class UserController {

  private final UserService userService;

  public UserController(UserService userService) {
    this.userService = userService;
  }

  @PostMapping(path = "/user")
  public ResponseEntity<UserDto> createUser(@RequestBody UserDto userDto) {
    UserDto userDtoNew = userService.saveOne(userDto);
    return ResponseEntity.ok(userDtoNew);
  }

  @PostMapping(path = "/users")
  public ResponseEntity<List<UserDto>> createUsers(@RequestBody List<UserDto> userDTOs) {
    List<UserDto> usersDto = userService.saveMany(userDTOs);
    return ResponseEntity.ok(usersDto);
  }

  @GetMapping("/user/{id}")
  public ResponseEntity<UserDto> getUserById(@PathVariable Long id) {
    UserDto userDto = userService.getById(id);
    return ResponseEntity.ok(userDto);
  }

  @GetMapping("/users/{ids}")
  public ResponseEntity<List<UserDto>> getUsersByIds(@PathVariable Set<Long> ids) {
    List<UserDto> usersDto = userService.getByIds(ids);
    return ResponseEntity.ok(usersDto);
  }

  @GetMapping("/user/email/{email}")
  public ResponseEntity<UserDto> getUserByEmail(@PathVariable String email) {
    UserDto userDto = userService.getByEmail(email);
    return ResponseEntity.ok(userDto);
  }

  @PutMapping("/user/{id}")
  public ResponseEntity<UserDto> updateUser(@PathVariable Long id, @RequestBody UserDto userDto) {
    UserDto userDtoUpdated = userService.updateUser(id, userDto);
    return ResponseEntity.ok(userDtoUpdated);
  }

  @DeleteMapping("/user/{id}")
  public ResponseEntity<UserDto> deleteUserById(@PathVariable Long id) {
    UserDto userDto = userService.deleteById(id);
    return ResponseEntity.ok(userDto);
  }

  @GetMapping(path = "/user/test")
  public ResponseEntity<List<UserDto>> addTestUser() {
    List<UserDto> usersDto = userService.addTestUser();
    return ResponseEntity.ok(usersDto);
  }

  @GetMapping(path = "/user/deltest")
  public ResponseEntity<UserDto> deleteLast() {
    UserDto userDto = userService.delUserLast();
    return ResponseEntity.ok(userDto);
  }

  @GetMapping(path = "/user/last")
  public ResponseEntity<UserDto> getLast() {
    UserDto userDto = userService.getUserLast();
    return ResponseEntity.ok(userDto);
  }

  @GetMapping(path = "/users")
  public ResponseEntity<List<UserDto>> getAllU() {
    return ResponseEntity.ok(userService.getAllUsers());
  }

  @GetMapping(path = "/user/random")
  public ResponseEntity<UserDto> getRUse() {
    UserDto userDto = userService.getRandomUser();
    return ResponseEntity.ok(userDto);
  }

  @GetMapping(path = "/user/native/{n}")
  public ResponseEntity<List<UserDto>> getRangeIds(@PathVariable Integer n) {
    List<UserDto> usersDto = userService.getRangeIds(n);
    return ResponseEntity.ok(usersDto);
  }

  @GetMapping(path = "/user/jpql/{lastname}")
  public ResponseEntity<List<UserDto>> findByJPQL(@PathVariable String lastname) {
    List<UserDto> usersDto = userService.findByJPQL(lastname);
    return ResponseEntity.ok(usersDto);
  }
}
