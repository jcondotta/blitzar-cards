package com.blitzar.cards.domain;

import java.util.function.Supplier;

public enum CardModel {

    STANDARD(StandardCard::new),
    PREMIUM(PremiumCard::new);

    private Supplier<Card> instance;

    CardModel(Supplier<Card> instance) {
        this.instance = instance;
    }

    public Card getInstance() {
        return instance.get();
    }
}
