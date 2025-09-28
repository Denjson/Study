package com.study.userservice.repository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.study.userservice.entity.Card;

@Repository
public interface CardRepository extends CrudRepository<Card, Long> {

  Optional<List<Card>> findByIdIn(Set<Long> ids);

  Optional<Card> findTopByOrderByIdDesc();
}
