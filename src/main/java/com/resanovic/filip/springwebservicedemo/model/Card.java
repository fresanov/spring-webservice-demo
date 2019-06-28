package com.resanovic.filip.springwebservicedemo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Card {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String cardNumber;
    private String cardBrand;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_fk_cardholder")
    @JsonIgnore
    private Cardholder cardholder;
}
