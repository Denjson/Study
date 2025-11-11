package com.study.userservice.mappers;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.study.userservice.dto.CardDto;
import com.study.userservice.entity.Card;

@Component
public class CardMapper {

  public CardDto toDTO(Card card) {
    if (card == null) {
      return null;
    }
    return new CardDto(
        card.getId(), card.getUserId(), card.getNumber(), card.getHolder(), card.getDateEx());
  }

  public List<CardDto> toDTOs(List<Card> cards) {
    return cards.stream().map(this::toDTO).collect(Collectors.toList());
  }

  public Card toEntity(CardDto cardDto) {
    if (cardDto == null) {
      return null;
    }
    Card card = new Card();
    card.setId(cardDto.getId());
    card.setUserId(cardDto.getUserId());
    card.setNumber(cardDto.getNumber());
    card.setHolder(cardDto.getHolder());
    card.setDateEx(cardDto.getDateEx());
    return card;
  }

  public List<Card> manyToEntity(List<CardDto> cardDTOs) {
    return cardDTOs.stream().map(this::toEntity).collect(Collectors.toList());
  }
}
