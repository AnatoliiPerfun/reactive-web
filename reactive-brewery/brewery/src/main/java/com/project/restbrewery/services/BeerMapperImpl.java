package com.project.restbrewery.services;

import com.project.restbrewery.domain.Beer;
import com.project.restbrewery.web.mappers.BeerMapper;
import com.project.restbrewery.web.model.BeerDto;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
public class BeerMapperImpl implements BeerMapper {
    @Override
    public BeerDto beerToBeerDto(Beer beer) {
        if (beer == null) {
            return null;
        }
        BeerDto.BeerDtoBuilder beerDto = BeerDto.builder();
        beerDto.id(beer.getId());
        beerDto.beerName(beer.getBeerName());
        if (beer.getBeerStyle() != null) {
            beerDto.beerStyle(beer.getBeerStyle().name());
        }
        beerDto.upc(beer.getUpc());
        beerDto.price(beer.getPrice());
        beerDto.quantityOnHand(beer.getQuantityOnHand());
        return beerDto.build();
    }

    @Override
    public BeerDto beerToBeerDtoWithInventory(Beer beer) {
        return null;
    }

    @Override
    public BeerDto beerToBeerDto(Mono<Beer> byUpc) {
        return null;
    }



}
