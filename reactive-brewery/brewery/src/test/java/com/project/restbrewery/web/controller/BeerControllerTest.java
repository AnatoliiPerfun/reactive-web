package com.project.restbrewery.web.controller;

import com.project.restbrewery.bootstrap.BeerLoader;
import com.project.restbrewery.services.BeerService;
import com.project.restbrewery.web.model.BeerDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.reactive.server.WebTestClient;

@WebFluxTest(BeerController.class)
class BeerControllerTest {
    @Autowired
    WebTestClient webTestClient;
    @MockBean
    BeerService beerService;
    BeerDto validBeer;

    @BeforeEach
    void setUp() {
        validBeer = BeerDto.builder()
                .beerName("Test beer")
                .beerStyle("IPA")
                .upc(BeerLoader.BEER_1_UPC)
                .build();
    }
    @Test
    void getBeerById() {
    }
}