package com.study.userservice.dto;

import java.time.LocalDateTime;

public class CardDto {

  private Long id;

  private Long userId;

  private Integer number;

  private String holder;

  private LocalDateTime dateEx;

  public CardDto(Long id, Long userId, Integer number, String holder, LocalDateTime dateEx) {
    this.id = id;
    this.userId = userId;
    this.number = number;
    this.holder = holder;
    this.dateEx = dateEx;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Long getUserId() {
    return userId;
  }

  public void setUserId(Long userId) {
    this.userId = userId;
  }

  public Integer getNumber() {
    return number;
  }

  public void setNumber(Integer number) {
    this.number = number;
  }

  public String getHolder() {
    return holder;
  }

  public void setHolder(String holder) {
    this.holder = holder;
  }

  public LocalDateTime getDateEx() {
    return dateEx;
  }

  public void setDateEx(LocalDateTime dateEx) {
    this.dateEx = dateEx;
  }

  @Override
  public String toString() {
    return "CardDto [id="
        + id
        + ", userId="
        + userId
        + ", number="
        + number
        + ", holder="
        + holder
        + ", dateEx="
        + dateEx
        + "]";
  }
}
