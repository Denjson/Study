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
import com.study.userservice.entity.Card;
import com.study.userservice.entity.User;
import com.study.userservice.service.CardService;
import com.study.userservice.service.UserService;

@RestController
@RequestMapping(path = "/demo") // http://localhost:8080/demo
public class CardController {

  private final UserService userService;
  private final CardService cardService;

  public CardController(UserService userService, CardService cardService) {
    this.userService = userService;
    this.cardService = cardService;
  }

  @PostMapping(path = "/addcard")
  // {"userId":402,"number":"123456","holder":"CardHolder","dateEx":"2022-02-16T10:22:15"}
  public ResponseEntity<CardDto> createCard(@RequestBody Card c) {
    CardDto cardDto = cardService.saveOne(c);
    return ResponseEntity.ok(cardDto);
  }

  @PostMapping(path = "/addallcards") // adding array in Postman:
  //  [{"userId":402,"number":"111111","holder":"CardHolder","dateEx":"2022-02-16T10:22:15"},
  //   {"userId":402,"number":"222222","holder":"CardHolder","dateEx":"2022-02-16T10:22:15"}]
  public ResponseEntity<List<CardDto>> createCards(@RequestBody List<Card> cards) {
    List<CardDto> cardsDto = cardService.saveMany(cards);
    return ResponseEntity.ok(cardsDto);
  }

  @GetMapping("/card/{id}")
  public ResponseEntity<CardDto> getCardById(@PathVariable Long id) {
    CardDto cardDto = cardService.getById(id);
    return ResponseEntity.ok(cardDto);
  }

  @GetMapping("/cards/{ids}") // http://localhost:8080/cards/1,5,3
  public ResponseEntity<List<CardDto>> getCardsByIds(@PathVariable Set<Long> ids) {
    List<CardDto> cardsDto = cardService.getByIds(ids);
    return ResponseEntity.ok(cardsDto);
  }

  @PutMapping("/updatecard/{id}")
  // {"userId":"552","number":"987654","holder":"CannotChangeCardHolder","dateEx":"2022-02-16T10:22:15"}
  public ResponseEntity<CardDto> updateCard(@PathVariable Long id, @RequestBody Card c) {
    CardDto cardDto = cardService.updateCard(id, c);
    return ResponseEntity.ok(cardDto);
  }

  @GetMapping(path = "/addrandomcard") // http://localhost:8080/demo/addcard
  public Card getCard() {
    User u = userService.getRandomUser();
    Card c = cardService.addRandomCard(u);
    return c;
  }

  @GetMapping(path = "/allcards") // http://localhost:8080/demo/allcards
  public List<CardDto> getAllCards() {
    return cardService.getAllCards();
  }

  @DeleteMapping("/delcard/{id}")
  public ResponseEntity<CardDto> deleteCardById(@PathVariable Long id) {
    CardDto cardDto = cardService.deleteById(id);
    return ResponseEntity.ok(cardDto);
  }

  @GetMapping(path = "/dellastcard") // http://localhost:8080/demo/delcard
  public Iterable<Card> deleteLastCard() {
    return cardService.delCardLast();
  }
}
