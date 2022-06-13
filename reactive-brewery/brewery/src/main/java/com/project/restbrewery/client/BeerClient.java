package com.project.restbrewery.client;

import com.project.restbrewery.web.model.BeerDto;
import com.project.restbrewery.web.model.BeerPagedList;
import org.springframework.http.ResponseEntity;
import reactor.core.publisher.Mono;

import java.util.UUID;

public interface BeerClient {

    Mono<BeerDto> getBeerById(UUID id, Boolean showInventoryOnHand);

    Mono<BeerPagedList> listBeers(Integer pageNumber,
                                  Integer pageSize,
                                  String beerName,
                                  String beerStyle,
                                  Boolean showInvenotryOnHand);

    Mono<ResponseEntity<Void>> createBeer(BeerDto BeerDto);

    Mono<ResponseEntity<Void>> updateBeer(UUID beerId, BeerDto beerDto);

    Mono<ResponseEntity<Void>> deleteBeerById(Integer id);

    Mono<BeerDto> getBeerByUPC(String upc);
}
