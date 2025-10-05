package com.study.userservice.dto;

import java.io.Serializable;
import java.time.LocalDateTime;

public class UserDto implements Serializable {

  private static final long serialVersionUID = -4858846000871596637L;

  private Long id;

  private String name;

  private String surname;

  private LocalDateTime date;

  private String email;

  public UserDto() {}

  public UserDto(Long id, String name, String surname, LocalDateTime date, String email) {
    this.id = id;
    this.name = name;
    this.surname = surname;
    this.date = date;
    this.email = email;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getSurname() {
    return surname;
  }

  public void setSurname(String surname) {
    this.surname = surname;
  }

  public LocalDateTime getDate() {
    return date;
  }

  public void setDate(LocalDateTime date) {
    this.date = date;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  @Override
  public String toString() {
    return "User [id="
        + id
        + ", name="
        + name
        + ", surname="
        + surname
        + ", date="
        + date
        + ", email="
        + email
        + "]";
  }
}
