package com.orbitallpayments.hack22.controllers;

import com.orbitallpayments.hack22.domains.Card;
import com.orbitallpayments.hack22.services.CardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/cards")
public class CardController {

    @Autowired
    private CardService cardService;

    @GetMapping()
    public ResponseEntity<List<Card>> findAll() {
        List<Card> customers = cardService.findAll();
        return ResponseEntity.ok(customers);
    }

    @PostMapping()
    public ResponseEntity<Card> save(@RequestBody Card card) {
        Card savedCard = cardService.save(card);
        return new ResponseEntity(savedCard, HttpStatus.CREATED);

    }

    @PutMapping("/{id}")
    public ResponseEntity update(@PathVariable Long id, @RequestBody Card card) {

        Optional<Card> foundCard = cardService.findById(id);

        if (!foundCard.isPresent())
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("card not found.");

        var cardDomain = foundCard.get();
        cardDomain.setCardNumber(card.getCardNumber());
        cardDomain.setEmbossName(card.getEmbossName());
        cardDomain.setCustomerName(card.getCustomerName());
        cardDomain.setDocumentNumber(card.getDocumentNumber());
        cardDomain.setMotherName(card.getMotherName());
        cardDomain.setAddress(card.getAddress());
        cardDomain.setCity(card.getCity());

        return ResponseEntity.status(HttpStatus.OK).body(cardService.save(cardDomain));

    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteById(@PathVariable Long id) {

        if (!cardService.findById(id).isPresent())
            return new ResponseEntity("Card not found!", HttpStatus.NOT_FOUND);
        else {
            cardService.deleteById(id);
            return new ResponseEntity("Removed Card!", HttpStatus.OK);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Card> findById(@PathVariable Long id) {

        Optional<Card> fetchedCard = cardService.findById(id);

        if (!fetchedCard.isPresent())
            return new ResponseEntity("Card not found!", HttpStatus.NOT_FOUND);
        else {
            cardService.findById(id);
            return new ResponseEntity(fetchedCard.get(), HttpStatus.OK);
        }
    }


}