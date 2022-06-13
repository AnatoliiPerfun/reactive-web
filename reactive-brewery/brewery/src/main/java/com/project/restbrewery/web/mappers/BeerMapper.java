package com.project.restbrewery.web.mappers;

import com.project.restbrewery.web.model.BeerDto;
import com.project.restbrewery.domain.Beer;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import reactor.core.publisher.Mono;


@Mapper(uses = {DateMapper.class})
public interface BeerMapper {

    @Mapping(target = "quantityOnHand", ignore = true)
    default BeerDto beerToBeerDto(Beer beer) {
        return null;
    }
    default BeerDto beerToBeerDtoWithInventory(Beer beer) {
        return null;
    }

    BeerDto beerToBeerDto(Mono<Beer> byUpc);

}
