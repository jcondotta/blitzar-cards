package com.blitzar.cards.web.controller;

import com.blitzar.cards.service.GetCardService;
import com.blitzar.cards.web.dto.CardDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cards")
public class GetCardController {

    private final GetCardService getCardService;

    @Autowired
    public GetCardController(GetCardService getCardService) {
        this.getCardService = getCardService;
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> byId(@PathVariable("id") Long id){
        CardDTO cardDTO = getCardService.byId(id);
        return ResponseEntity.ok().body(cardDTO);
    }
}