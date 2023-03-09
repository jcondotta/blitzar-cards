package com.blitzar.cards.web.controller;

import com.blitzar.cards.domain.CardModel;
import com.blitzar.cards.service.AddCardDelegate;

public class AddCardRequest implements AddCardDelegate {

    private CardModel cardModel;
    private String cardholderName;

    @Override
    public CardModel getCardModel() {
        return cardModel;
    }

    public void setCardModel(CardModel cardModel) {
        this.cardModel = cardModel;
    }

    @Override
    public String getCardholderName() {
        return cardholderName;
    }

    public void setCardholderName(String cardholderName) {
        this.cardholderName = cardholderName;
    }
}