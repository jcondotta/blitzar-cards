package com.blitzar.cards.web.controller;

import com.blitzar.cards.TestMySQLContainer;
import com.blitzar.cards.argumentprovider.InvalidStringArgumentProvider;
import com.blitzar.cards.domain.Card;
import com.blitzar.cards.repository.CardRepository;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.testcontainers.shaded.com.google.common.collect.Iterables;

import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.*;

@TestInstance(Lifecycle.PER_CLASS)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class AddCardControllerTest extends TestMySQLContainer {

    private String currentTestName;
    private RequestSpecification requestSpecification;

    @Autowired
    private CardRepository cardRepository;

    @BeforeAll
    public static void beforeAll(@LocalServerPort int serverHttpPort){
        RestAssured.port = serverHttpPort;
        RestAssured.filters(new RequestLoggingFilter(), new ResponseLoggingFilter());
    }

    @BeforeEach
    public void beforeEach(TestInfo testInfo) {
        this.currentTestName = testInfo.getTestMethod().orElseThrow().getName();
        this.requestSpecification = new RequestSpecBuilder()
                .setBasePath("/cards")
                .build()
                .contentType(ContentType.JSON);
    }

    @Test
    public void givenValidRequest_whenAddCard_thenReturnCreated(){
        AddCardRequest addCardRequest = new AddCardRequest();
        addCardRequest.setCardholderName(currentTestName);

        given()
            .spec(requestSpecification)
            .body(addCardRequest)
        .when()
            .post()
        .then()
            .statusCode(HttpStatus.CREATED.value());

        Iterable<Card> all = cardRepository.findAll();
        Iterables.filter(cardRepository.findAll(), card -> card.getCardholderName().equals(currentTestName))
                .forEach(card -> {
                    assertThat(card.getSecurityCode()).hasSize(3);
                });
    }

    @ParameterizedTest
    @ArgumentsSource(InvalidStringArgumentProvider.class)
    public void givenInvalidCardholderName_whenAddCard_thenReturnBadRequest(String invalidCardholderName){
        AddCardRequest addCardRequest = new AddCardRequest();
        addCardRequest.setCardholderName(invalidCardholderName);

        given()
            .spec(requestSpecification)
            .body(addCardRequest)
        .when()
            .post()
        .then()
            .statusCode(HttpStatus.BAD_REQUEST.value());
    }
}
