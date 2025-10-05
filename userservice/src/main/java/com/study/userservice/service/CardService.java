package com.study.userservice.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.apache.commons.collections4.IterableUtils;
import org.springframework.stereotype.Service;

import com.study.userservice.dto.CardDto;
import com.study.userservice.entity.Card;
import com.study.userservice.entity.User;
import com.study.userservice.repository.CardRepository;
import com.study.userservice.repository.UserRepository;

@Service
public class CardService implements CardServiceInterface {

  private final CardRepository cardRepository;
  private final UserRepository userRepository;

  public CardService(CardRepository cardRepository, UserRepository userRepository) {
    this.cardRepository = cardRepository;
    this.userRepository = userRepository;
  }

  private CardDto convertToDto(Card card) {
    return new CardDto(
        card.getId(), card.getUserId(), card.getNumber(), card.getHolder(), card.getDateEx());
  }

  private List<CardDto> convertToDTOs(List<Card> cards) {
    return cards.stream().map(this::convertToDto).collect(Collectors.toList());
  }

  public CardDto saveOne(Card c) {
    Optional<User> userOptional = userRepository.findById(c.getUserId());
    if (userOptional.isPresent()) {
      cardRepository.save(c);
      System.out.println("User found. Card added: " + c.toString());
    } else {
      System.out.println("User with ID is not found: " + c.getUserId());
      return null;
    }
    return convertToDto(c);
  }

  public List<CardDto> saveMany(List<Card> cards) {
    Set<Long> usersFromCards = cards.stream().map(Card::getUserIdLong).collect(Collectors.toSet());
    //    System.out.println("List of Users id from cards: " + usersFromCards);
    Optional<List<User>> usersFound = userRepository.findByIdIn(usersFromCards);
    //    System.out.println("List of Users id from cards: " + usersFound.get().toString());
    if (usersFound.get().size() == usersFromCards.size()) {
      cardRepository.saveAll(cards);
      System.out.println("All users found. Saved Cards: " + cards);
      return convertToDTOs(cards);
    }
    return null;
  }

  public CardDto getById(Long id) {
    Optional<Card> cardOptional = cardRepository.findById(id);
    Card card;
    if (cardOptional.isPresent()) {
      card = cardOptional.get();
      System.out.println("Card found: " + card.toString());
    } else {
      System.out.println("Card with ID " + id + " not found.");
      return null;
    }
    return convertToDto(card);
  }

  public List<CardDto> getByIds(Set<Long> ids) {
    List<Card> cards = cardRepository.findByIdIn(ids).get();
    System.out.println("Users found: " + cards.toString());
    return convertToDTOs(cards);
  }

  public List<CardDto> getAllCards() {
    List<Card> cards = IterableUtils.toList(cardRepository.findAll());
    List<CardDto> cardsDto = this.convertToDTOs(cards);
    return cardsDto;
  }

  public CardDto deleteById(Long id) {
    Optional<Card> cardOptional = cardRepository.findById(id);
    Card card;
    if (cardOptional.isPresent()) {
      card = cardOptional.get();
      System.out.println("Card found: " + card.toString());
      cardRepository.deleteById(id);
    } else {
      System.out.println("Card with ID " + id + " not found.");
      return null;
    }
    return convertToDto(card);
  }

  public Iterable<Card> delCardLast() {
    if (cardRepository.count() > 0) {
      Optional<Card> u = cardRepository.findTopByOrderByIdDesc();
      System.out.println("Delete last card: " + cardRepository.findTopByOrderByIdDesc().get());
      cardRepository.delete(u.get());
    }
    return cardRepository.findAll();
  }

  public CardDto updateCard(Long id, Card cardDetails) {
    Optional<User> userOptional = userRepository.findById((long) cardDetails.getUserId());
    Optional<Card> cardOptional = cardRepository.findById(id);
    Card card;
    if (userOptional.isPresent() && cardOptional.isPresent()) {
      card = cardOptional.get();
      card.setUserId(cardDetails.getUserId());
      card.setHolder(userOptional.get().getName());
      card.setNumber(cardDetails.getNumber());
      card.setDateEx(cardDetails.getDateEx());
      cardRepository.save(card);
      System.out.println("Card updated: " + card.toString());
    } else {
      System.out.println("Card with ID " + cardDetails.getId() + " not found.");
      return null;
    }
    return convertToDto(card);
  }

  public Card addRandomCard(User u) {
    Card c = new Card();
    c.setUserId(u.getId());
    c.setNumber((int) (Math.random() * 10000000) + 1000000);
    c.setHolder(u.getName());
    c.setDateEx(LocalDateTime.now());
    System.out.println("From UserService - Card added to Random user: " + c.toString());
    cardRepository.save(c);
    return c;
  }
}
