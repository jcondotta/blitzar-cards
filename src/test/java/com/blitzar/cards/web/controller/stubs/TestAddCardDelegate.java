package com.blitzar.cards.web.controller.stubs;

import com.blitzar.cards.service.AddCardDelegate;
import com.blitzar.cards.web.controller.AddCardRequest;

public class TestAddCardDelegate implements AddCardDelegate {

    private String cardholderName;

    public TestAddCardDelegate() {
        this.cardholderName = "Jefferson Condotta";
    }

    @Override
    public String getCardholderName() {
        return cardholderName;
    }

    public TestAddCardDelegate setCardholderName(String cardholderName) {
        this.cardholderName = cardholderName;
        return this;
    }

    public AddCardRequest buildCardRequest(){
        return new AddCardRequest(this);
    }
}
