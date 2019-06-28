package com.resanovic.filip.springwebservicedemo.controller;

import com.resanovic.filip.springwebservicedemo.model.Card;
import com.resanovic.filip.springwebservicedemo.repository.CardRepository;
import com.resanovic.filip.springwebservicedemo.repository.CardholderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CardController {

    private CardRepository cardRepository;
    private CardholderRepository cardholderRepository;

    @Autowired
    public CardController(CardRepository cardRepository, CardholderRepository cardholderRepository){
        this.cardRepository = cardRepository;
        this.cardholderRepository = cardholderRepository;

    }

    @GetMapping(("/cardholders/{cardholderId}/cards"))
    public List<Card> getAllCardsByCardholderId(@PathVariable (value = "cardholderId") Long cardholderId){
        return cardRepository.findByCardholderId(cardholderId);
    }

    @PostMapping("/cardholders/{cardholderId}/cards")
    public Card create(@PathVariable (value = "cardholderId") Long cardholderId, @RequestBody Card card){
        return cardholderRepository.findById(cardholderId).map(cardholder -> {
            card.setCardholder(cardholder);
            return cardRepository.save(card);
        }).orElseThrow(() -> new RuntimeException("CardholderId " + cardholderId + " not found"));
    }

    @PutMapping("/cardholders/{cardholderId}/cards/{cardId}")
    public Card updateCard(@PathVariable (value = "cardholderId") Long cardholderId,
                                           @PathVariable (value = "cardId") Long cardId,
                                           @RequestBody Card cardRequest){
        return cardRepository.findById(cardId).map(card -> {
            card.setCardBrand(cardRequest.getCardBrand());
            card.setCardNumber(cardRequest.getCardNumber());
            return cardRepository.save(card);
        }).orElseThrow(() -> new RuntimeException("CardId " + cardId + "not found"));
    }

    @DeleteMapping("/cardholders/{cardholderId}/cards/{cardId}")
    public ResponseEntity<?> deleteCard(@PathVariable (value = "cardholderId") Long cardholderId,
                                        @PathVariable (value = "cardId") Long cardId){
        return cardRepository.findByIdAndCardholderId(cardId, cardholderId).map(card -> {
            cardRepository.delete(card);
            return ResponseEntity.ok().build();
        }).orElseThrow(() -> new RuntimeException("Card not found with id " + cardId + " and CardholderId " + cardholderId));
    }

}
