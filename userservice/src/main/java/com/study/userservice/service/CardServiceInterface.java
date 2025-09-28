package com.study.userservice.service;

import java.util.List;
import java.util.Set;

import com.study.userservice.dto.CardDto;
import com.study.userservice.entity.Card;

public interface CardServiceInterface {

  public CardDto saveOne(Card c);

  public List<CardDto> saveMany(List<Card> u);

  public CardDto getById(Long id);

  public List<CardDto> getByIds(Set<Long> ids);

  public CardDto updateCard(Long id, Card cardDetails);

  public CardDto deleteById(Long id);

  public List<CardDto> getAllCards();

  //  public Card getCardLast();

  public Iterable<Card> delCardLast();

  //  public Iterable<Card> addTestCard();
}
