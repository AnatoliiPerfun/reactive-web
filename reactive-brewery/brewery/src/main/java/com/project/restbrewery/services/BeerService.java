package com.project.restbrewery.services;

import com.project.restbrewery.web.model.BeerDto;
import com.project.restbrewery.web.model.BeerPagedList;
import com.project.restbrewery.web.model.BeerStyleEnum;
import org.springframework.data.domain.PageRequest;
import reactor.core.publisher.Mono;

public interface BeerService {
    Mono<BeerPagedList> listBeers(String beerName, BeerStyleEnum beerStyle, PageRequest pageRequest, Boolean showInventoryOnHand);
    Mono<BeerDto> getById(Integer beerId, Boolean showInventoryOnHand);
    Mono<BeerDto> saveNewBeer(BeerDto beerDto);
    Mono<BeerDto>updateBeer(Integer beerId, BeerDto beerDto);
    Mono<BeerDto> getByUpc(String upc);
    void deleteBeerById(Integer beerId);
}
