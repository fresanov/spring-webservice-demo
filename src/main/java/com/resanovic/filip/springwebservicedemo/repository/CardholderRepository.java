package com.resanovic.filip.springwebservicedemo.repository;

import com.resanovic.filip.springwebservicedemo.model.Cardholder;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CardholderRepository extends JpaRepository<Cardholder, Long> {
}
