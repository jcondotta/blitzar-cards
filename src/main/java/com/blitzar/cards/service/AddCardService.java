package com.blitzar.cards.service;

import am.ik.yavi.builder.ValidatorBuilder;
import am.ik.yavi.core.ConstraintViolationsException;
import com.blitzar.cards.domain.Card;
import com.blitzar.cards.domain.StandardCard;
import com.blitzar.cards.repository.CardRepository;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.UUID;

@Service
public class AddCardService {

    private final CardRepository repository;

    @Autowired
    public AddCardService(CardRepository repository) {
        this.repository = repository;
    }

    public void addCard(AddCardDelegate delegate){
        Card standardCard = new StandardCard();

        ValidatorBuilder.<AddCardDelegate>of()
                .constraint(AddCardDelegate::getCardholderName, "cardholderName", c -> c.notBlank().message("card.cardholderName.notBlank"))
                .build()
                .applicative()
                .validate(delegate)
                .orElseThrow(violations -> new ConstraintViolationsException(violations));


        standardCard.setCardholderName(delegate.getCardholderName());
        standardCard.setCardNumber(UUID.randomUUID().toString());
        standardCard.setExpirationDate(LocalDate.now().plus(4, ChronoUnit.YEARS));
        standardCard.setSecurityCode(RandomStringUtils.randomNumeric(3));
        repository.save(standardCard);
    }
}