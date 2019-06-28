package com.resanovic.filip.springwebservicedemo.repository;

import com.resanovic.filip.springwebservicedemo.model.Card;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CardRepository extends JpaRepository<Card, Long> {
    List<Card> findByCardholderId(Long cardholderId);
    Optional<Card> findByIdAndCardholderId(Long id, Long postId);
}
