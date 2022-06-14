package com.project.restbrewery.web.controller;


import com.project.restbrewery.config.WebClient;
import com.project.restbrewery.web.model.BeerDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import reactor.core.publisher.Mono;
import reactor.netty.http.client.HttpClient;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class WebClientV2 {
    public static final String BASE_URL = "http://localhost:8080";
    public static final String BEER_V2_PATH = "api/v2/beer";
    WebClient webClient;

    @BeforeEach
    void setUp() {
        assert WebClient.builder() != null;
        webClient = (WebClient) WebClient.builder()
                .baseUrl(BASE_URL)
                .clientConnector(new ReactorClientHttpConnector(HttpClient.create()
                        .wiretap(true)))
                .build();
    }

    @Test
    void getBeerById() throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(1);

        Mono<BeerDto> beerDtoMono = webClient.get().uri(BEER_V2_PATH + "/" + 1)
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(BeerDto.class);

        beerDtoMono.subscribe(beer -> {
           assertThat(beer).isNotNull();
           assertThat(beer.getBeerName()).isNotNull();

           countDownLatch.countDown();
        });
        countDownLatch.await(1000, TimeUnit.MILLISECONDS);
        assertThat(countDownLatch.getCount()).isEqualTo(0);
    }

    @Test
    void getBeerByIdNotFound() throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(1);

        Mono<BeerDto> beerDtoMono = webClient.get().uri(BEER_V2_PATH + "/" + 1333)
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(BeerDto.class);

        beerDtoMono.subscribe(beer -> {

        }, throwable -> {
            countDownLatch.countDown();
        });
        countDownLatch.await(2000, TimeUnit.MILLISECONDS);
        assertThat(countDownLatch.getCount()).isEqualTo(0);
    }
}
