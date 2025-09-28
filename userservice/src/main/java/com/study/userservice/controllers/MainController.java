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
import com.study.userservice.entity.User;
import com.study.userservice.service.UserService;

@RestController
@RequestMapping(path = "/demo") // http://localhost:8080/demo
public class MainController {

  private final UserService userService;

  public MainController(UserService userService) {
    this.userService = userService;
  }

  @PostMapping(path = "/add")
  // {"name":"XXX","surname":"ManX","date":"2022-02-16T10:22:15", "email":"come@with.me"}
  public ResponseEntity<UserDto> createUser(@RequestBody User user) {
    UserDto userDto = userService.saveOne(user);
    return ResponseEntity.ok(userDto);
  }

  @PostMapping(path = "/addall") // adding array in Postman:
  //  [{"name":"XXX","surname":"ManX", "date":"2022-02-16T10:22:15", "email":"come@with.me"},
  //   {"name":"YYY","surname":"WomanY", "date":"2022-02-16T10:22:15", "email":"you@and.me"}]
  public ResponseEntity<List<UserDto>> createUsers(@RequestBody List<User> users) {
    List<UserDto> usersDto = userService.saveMany(users);
    return ResponseEntity.ok(usersDto);
  }

  @GetMapping("/{id}")
  public ResponseEntity<UserDto> getUserById(@PathVariable Long id) {
    UserDto userDto = userService.getById(id);
    return ResponseEntity.ok(userDto);
  }

  @GetMapping("/ids/{ids}") // http://localhost:8080/demo/ids/1,5,3
  public ResponseEntity<List<UserDto>> getUsersByIds(@PathVariable Set<Long> ids) {
    List<UserDto> usersDto = userService.getByIds(ids);
    return ResponseEntity.ok(usersDto);
  }

  @GetMapping("/email/{email}") // http://localhost:8080/demo/email/a@me.com
  public ResponseEntity<UserDto> getUserByEmai(@PathVariable String email) {
    UserDto userDto = userService.getByEmail(email);
    return ResponseEntity.ok(userDto);
  }

  @PutMapping("/update/{id}")
  // {"name":"UpXXX","surname":"UpManX", "date":"2022-02-16T10:22:15", "email":"UPcome@with.me"}
  public ResponseEntity<UserDto> updateUser(@PathVariable Long id, @RequestBody User user) {
    UserDto userDto = userService.updateUser(id, user);
    return ResponseEntity.ok(userDto);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<UserDto> deleteUserById(@PathVariable Long id) {
    UserDto userDto = userService.deleteById(id);
    return ResponseEntity.ok(userDto);
  }

  @GetMapping(path = "/addtest") // http://localhost:8080/demo/addnew
  public Iterable<User> addTestUser() {
    return userService.addTestUser();
  }

  @GetMapping(path = "/deletelast") // http://localhost:8080/demo/deletelast
  public User deleteLast() {
    return userService.delUserLast();
  }

  @GetMapping(path = "/getlast") // http://localhost:8080/demo/getlast
  public User getLast() {
    User u = userService.getUserLast();
    return u;
  }

  @GetMapping(path = "/all") // http://localhost:8080/demo/all
  public List<UserDto> getAllU() {
    return userService.getAllUsers();
  }

  @GetMapping(path = "/getrandom") // http://localhost:8080/demo/getrandom
  public User getRUse() {
    User u = userService.getRandomUser();
    return u;
  }

  @GetMapping(path = "/native/{n}") // http://localhost:8080/demo/native/400
  public List<UserDto> getRangeIds(@PathVariable Integer n) {
    return userService.getRangeIds(n);
  }

  @GetMapping(path = "/jpql/{lastname}") // http://localhost:8080/demo/jpql/ManX
  public List<UserDto> findByJPQL(@PathVariable String lastname) {
    return userService.findByJPQL(lastname);
  }
}
