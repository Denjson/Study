package com.study.userservice.repository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.study.userservice.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

  Optional<User> findTopByOrderByIdDesc();

  Optional<User> getByEmail(String email);

  Optional<List<User>> findByIdIn(Set<Long> ids);

  @Query(value = "SELECT * FROM users WHERE id > :n", nativeQuery = true)
  List<User> findIdsNative(Integer n);

  @Query("SELECT u FROM User u WHERE u.surname = :surname")
  List<User> findByLastNameJPQL(@Param("surname") String surname);
}
