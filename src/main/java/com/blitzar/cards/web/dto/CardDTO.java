package com.blitzar.cards.web.dto;

import com.blitzar.cards.domain.Card;

public record CardDTO(long cardId, String cardholderName) {

    public CardDTO(Card card) {
        this(card.getCardId(), card.getCardholderName());
    }
}