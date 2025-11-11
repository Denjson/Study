package com.study.userservice.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.apache.commons.collections4.IterableUtils;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;

import com.study.userservice.dto.CardDto;
import com.study.userservice.dto.UserDto;
import com.study.userservice.entity.Card;
import com.study.userservice.entity.User;
import com.study.userservice.exceptions.IdNotFoundException;
import com.study.userservice.mappers.CardMapper;
import com.study.userservice.repository.CardRepository;
import com.study.userservice.repository.UserRepository;
import com.study.userservice.service.interfaces.CardService;

@Service
public class CardServiceImpl implements CardService {

  private final CardRepository cardRepository;
  private final UserRepository userRepository;
  private final CardMapper cardMapper;

  public CardServiceImpl(
      CardRepository cardRepository, UserRepository userRepository, CardMapper cardMapper) {
    this.cardRepository = cardRepository;
    this.userRepository = userRepository;
    this.cardMapper = cardMapper;
  }

  @CacheEvict(value = "cards", key = "0")
  public CardDto saveOne(Card c) {
    Optional<User> userOptional = userRepository.findById(c.getUserId());
    if (userOptional.isPresent()) {
      cardRepository.save(c);
      System.out.println("User found. Card added: " + c.toString());
    } else {
      System.out.println("User with ID is not found: " + c.getUserId());
      return null;
    }
    return cardMapper.toDTO(c);
  }

  @CacheEvict(value = "cards", key = "0")
  public List<CardDto> saveMany(List<Card> cards) {
    Set<Long> usersFromCards = cards.stream().map(Card::getUserId).collect(Collectors.toSet());
    Optional<List<User>> usersFound = userRepository.findByIdIn(usersFromCards);
    if (usersFound.get().size() == usersFromCards.size()) {
      cardRepository.saveAll(cards);
      System.out.println("All users found. Saved Cards: " + cards);
      return cardMapper.toDTOs(cards);
    }
    return null;
  }

  @Cacheable(value = "cards", key = "#id")
  public CardDto getById(Long id) {
    Card card =
        cardRepository
            .findById(id)
            .orElseThrow(() -> new IdNotFoundException("Card not found with id: " + id));
    return cardMapper.toDTO(card);
  }

  public List<CardDto> getByIds(Set<Long> ids) {
    List<Card> cards = cardRepository.findByIdIn(ids).get();
    if (cards.isEmpty()) {
      throw new IdNotFoundException("Cards not found with ids: " + ids);
    }
    System.out.println("Cards found: " + cards.toString());
    return cardMapper.toDTOs(cards);
  }

  @Cacheable(value = "cards", key = "0")
  public List<CardDto> getAllCards() {
    List<Card> cards = IterableUtils.toList(cardRepository.findAll());
    List<CardDto> cardsDto = cardMapper.toDTOs(cards);
    return cardsDto;
  }

  @Caching(
      evict = {@CacheEvict(value = "cards", key = "#id"), @CacheEvict(value = "cards", key = "0")})
  public CardDto deleteById(Long id) {
    Card card =
        cardRepository
            .findById(id)
            .orElseThrow(() -> new IdNotFoundException("Card not found with id: " + id));
    cardRepository.deleteById(id);
    return cardMapper.toDTO(card);
  }

  @CacheEvict(value = "cards", key = "0")
  public List<CardDto> delCardLast() {
    if (cardRepository.count() > 0) {
      Optional<Card> u = cardRepository.findTopByOrderByIdDesc();
      System.out.println("Delete last card: " + cardRepository.findTopByOrderByIdDesc().get());
      cardRepository.delete(u.get());
    }
    List<Card> cards = IterableUtils.toList(cardRepository.findAll());
    return cardMapper.toDTOs(cards);
  }

  @CachePut(value = "cards", key = "#id")
  @CacheEvict(value = "cards", key = "0")
  public CardDto updateCard(Long id, Card cardDetails) {
    Optional<User> userOptional = userRepository.findById(cardDetails.getUserId());
    Card card =
        cardRepository
            .findById(id)
            .orElseThrow(() -> new IdNotFoundException("Card not found with id: " + id));
    if (userOptional.isPresent()) {
      card.setUserId(cardDetails.getUserId());
      card.setHolder(userOptional.get().getName());
      card.setNumber(cardDetails.getNumber());
      card.setDateEx(cardDetails.getDateEx());
      cardRepository.save(card);
      System.out.println("Card updated: " + card.toString());
    } else {
      System.out.println("User with ID " + cardDetails.getUserId() + " not found.");
      throw new IdNotFoundException("User not found with id: " + cardDetails.getUserId());
    }
    return cardMapper.toDTO(card);
  }

  @CacheEvict(value = "cards", key = "0")
  public CardDto addRandomCard(UserDto userDto) {
    Card c = new Card();
    c.setUserId(userDto.getId());
    c.setNumber((int) (Math.random() * 10000000) + 1000000);
    c.setHolder(userDto.getName());
    c.setDateEx(LocalDateTime.now());
    System.out.println("From UserService - Card added to Random user: " + c.toString());
    cardRepository.save(c);
    return cardMapper.toDTO(c);
  }
}
