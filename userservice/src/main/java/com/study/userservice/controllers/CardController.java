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

import com.study.userservice.dto.CardDto;
import com.study.userservice.dto.UserDto;
import com.study.userservice.entity.Card;
import com.study.userservice.service.interfaces.CardService;
import com.study.userservice.service.interfaces.UserService;

@RestController
@RequestMapping(path = "/api/v1")
public class CardController {

  private final UserService userService;
  private final CardService cardService;

  public CardController(UserService userService, CardService cardService) {
    this.userService = userService;
    this.cardService = cardService;
  }

  @PostMapping(path = "/card")
  public ResponseEntity<CardDto> createCard(@RequestBody Card c) {
    CardDto cardDto = cardService.saveOne(c);
    return ResponseEntity.ok(cardDto);
  }

  @PostMapping(path = "/cards")
  public ResponseEntity<List<CardDto>> createCards(@RequestBody List<Card> cards) {
    List<CardDto> cardsDto = cardService.saveMany(cards);
    return ResponseEntity.ok(cardsDto);
  }

  @GetMapping("/card/{id}")
  public ResponseEntity<CardDto> getCardById(@PathVariable Long id) {
    CardDto cardDto = cardService.getById(id);
    return ResponseEntity.ok(cardDto);
  }

  @GetMapping("/cards/{ids}")
  public ResponseEntity<List<CardDto>> getCardsByIds(@PathVariable Set<Long> ids) {
    List<CardDto> cardsDto = cardService.getByIds(ids);
    return ResponseEntity.ok(cardsDto);
  }

  @PutMapping("/card/{id}")
  public ResponseEntity<CardDto> updateCard(@PathVariable Long id, @RequestBody Card c) {
    CardDto cardDto = cardService.updateCard(id, c);
    return ResponseEntity.ok(cardDto);
  }

  @GetMapping(path = "/card/random")
  public ResponseEntity<CardDto> addCard() {
    UserDto userDto = userService.getRandomUser();
    CardDto card = cardService.addRandomCard(userDto);
    return ResponseEntity.ok(card);
  }

  @GetMapping(path = "/cards")
  public ResponseEntity<List<CardDto>> getAllCards() {
    List<CardDto> cardsDto = cardService.getAllCards();
    return ResponseEntity.ok(cardsDto);
  }

  @DeleteMapping("/card/{id}")
  public ResponseEntity<CardDto> deleteCardById(@PathVariable Long id) {
    CardDto cardDto = cardService.deleteById(id);
    return ResponseEntity.ok(cardDto);
  }

  @GetMapping(path = "/card/last")
  public ResponseEntity<List<CardDto>> deleteLastCard() {
    List<CardDto> cardsDto = cardService.delCardLast();
    return ResponseEntity.ok(cardsDto);
  }
}
