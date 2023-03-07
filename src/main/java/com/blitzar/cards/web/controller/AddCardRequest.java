package com.blitzar.cards.web.controller;

import com.blitzar.cards.service.AddCardDelegate;

public class AddCardRequest implements AddCardDelegate {

    private String cardholderName;

    @Override
    public String getCardholderName() {
        return cardholderName;
    }

    public void setCardholderName(String cardholderName) {
        this.cardholderName = cardholderName;
    }
}
