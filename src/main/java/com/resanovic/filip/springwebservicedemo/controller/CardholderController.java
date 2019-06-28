package com.resanovic.filip.springwebservicedemo.controller;

import com.resanovic.filip.springwebservicedemo.model.Cardholder;
import com.resanovic.filip.springwebservicedemo.repository.CardholderRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping({"/cardholders"})
public class CardholderController {

    private CardholderRepository cardholderRepository;

    @Autowired
    CardholderController(CardholderRepository cardholderRepository){
        this.cardholderRepository = cardholderRepository;
    }

    @GetMapping
    public List findAll(){
        //log.debug("Got GET request for ALL");
        return cardholderRepository.findAll();
    }

    @GetMapping(path = {"/{id}"})
    public ResponseEntity<Cardholder> findById(@PathVariable long id){
        return cardholderRepository.findById(id)
                .map(record -> ResponseEntity.ok().body(record))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Cardholder create(@RequestBody Cardholder cardholder){
        return cardholderRepository.save(cardholder);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Cardholder> update(@PathVariable("id") long id, @RequestBody Cardholder cardholder){
        return cardholderRepository.findById(id)
                .map(record -> {
                    record.setName(cardholder.getName());
                    record.setEmail(cardholder.getEmail());
                    record.setPhone(cardholder.getPhone());
                    Cardholder updated = cardholderRepository.save(record);
                    return ResponseEntity.ok().body(updated);
                }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping(path = {"/{id}"})
    public ResponseEntity<?> delete(@PathVariable("id") long id){
        return cardholderRepository.findById(id)
                .map(record -> {
                    cardholderRepository.deleteById(id);
                    return ResponseEntity.ok().build();
                }).orElse(ResponseEntity.notFound().build());
    }

}
