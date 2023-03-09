package com.blitzar.cards.service;

import com.blitzar.cards.domain.CardModel;

public interface AddCardDelegate {

    CardModel getCardModel();
    String getCardholderName();

}
