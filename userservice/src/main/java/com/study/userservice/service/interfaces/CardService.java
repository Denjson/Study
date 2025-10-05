package com.study.userservice.service.interfaces;

import java.util.List;
import java.util.Set;

import com.study.userservice.dto.CardDto;
import com.study.userservice.dto.UserDto;
import com.study.userservice.entity.Card;

public interface CardService {

  public CardDto saveOne(Card c);

  public List<CardDto> saveMany(List<Card> u);

  public CardDto getById(Long id);

  public List<CardDto> getByIds(Set<Long> ids);

  public CardDto updateCard(Long id, Card cardDetails);

  public CardDto deleteById(Long id);

  public List<CardDto> getAllCards();

  public List<CardDto> delCardLast();

  public CardDto addRandomCard(UserDto u);
}
