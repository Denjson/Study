package com.study.userservice.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "card_info")
public class Card {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "id")
  private Long id;

  @Column(name = "user_id")
  private Long userId;

  @Column(name = "number")
  private Integer number;

  @Column(name = "holder")
  private String holder;

  @Column(name = "expiration_date")
  private LocalDateTime dateEx;

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
    return "Card [id="
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
