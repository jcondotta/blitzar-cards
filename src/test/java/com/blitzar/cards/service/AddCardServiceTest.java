package com.blitzar.cards.service;

import am.ik.yavi.core.ConstraintViolationsException;
import com.blitzar.cards.argumentprovider.InvalidStringArgumentProvider;
import com.blitzar.cards.domain.CardModel;
import com.blitzar.cards.repository.CardRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.TestInfo;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;
import org.junit.jupiter.params.provider.NullSource;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertThrowsExactly;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class AddCardServiceTest {

    private AddCardService addCardService;
    private String currentTestName;

    @Mock
    private CardRepository cardRepositoryMock;

    @BeforeEach
    public void beforeEach(TestInfo testInfo){
        this.currentTestName = testInfo.getTestMethod().orElseThrow().getName();
        this.addCardService = new AddCardService(cardRepositoryMock);
    }

//    @ParameterizedTest
//    @ArgumentsSource(InvalidStringArgumentProvider.class)
//    public void shouldThrowExceptionWhenCardholderNameIsBlank(String invalidCardholderName){
//        AddCardDelegate delegate = new AddCardDelegate() {
//            @Override
//            public CardModel getCardModel() {
//                return CardModel.STANDARD;
//            }
//            @Override
//            public String getCardholderName() {
//                return invalidCardholderName;
//            }
//        };
//
//        var exception = assertThrowsExactly(ConstraintViolationsException.class, () -> addCardService.addCard(delegate));
//        assertThat(exception.violations().size()).isEqualTo(1);
//
//        exception.violations().stream()
//                .findFirst()
//                .ifPresent(violation -> assertAll(
//                        () -> assertThat(violation.defaultMessageFormat()).isEqualTo("card.cardholderName.notBlank"),
//                        () -> assertThat(violation.name()).isEqualTo("cardholderName")
//                ));
//
//        verify(cardRepositoryMock, never()).save(any());
//    }
}
