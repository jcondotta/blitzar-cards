package com.blitzar.cards.service;

import am.ik.yavi.builder.ValidatorBuilder;
import am.ik.yavi.core.ConstraintViolationsException;
import com.blitzar.cards.domain.Card;
import com.blitzar.cards.repository.CardRepository;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
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

    public Card addCard(AddCardDelegate delegate){
        ValidatorBuilder.<AddCardDelegate>of()
                .constraint(AddCardDelegate::getCardholderName, "cardholderName",
                        c -> c.notNull().message("card.cardholderName.notBlank")
                                .predicate(s -> StringUtils.isNotBlank(s), "card.cardholderName.notBlank", "card.cardholderName.notBlank"))
                .build()
                .applicative()
                .validate(delegate)
                .orElseThrow(violations -> new ConstraintViolationsException(violations));

        var card = new Card();
        card.setCardholderName(delegate.getCardholderName());
        card.setCardNumber(UUID.randomUUID().toString());
        card.setExpirationDate(LocalDate.now().plus(4, ChronoUnit.YEARS));
        card.setSecurityCode(RandomStringUtils.randomNumeric(3).toCharArray());

        return repository.save(card);
    }
}