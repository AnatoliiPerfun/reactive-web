package com.project.restbrewery.services;

import com.project.restbrewery.domain.Beer;
import com.project.restbrewery.repositories.BeerRepository;
import com.project.restbrewery.web.mappers.BeerMapper;
import com.project.restbrewery.web.model.BeerDto;
import com.project.restbrewery.web.model.BeerPagedList;
import com.project.restbrewery.web.model.BeerStyleEnum;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.r2dbc.core.R2dbcEntityTemplate;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;


@Slf4j
@Service
@RequiredArgsConstructor
public class BeerServiceImpl implements BeerService {
    private final BeerRepository beerRepository;
    private final BeerMapper beerMapper;
    private final R2dbcEntityTemplate template;

    @Cacheable(cacheNames = "beerListCache", condition = "#showInventoryOnHand == false")
    @Override
    public Mono<BeerPagedList> listBeers(String beerName, BeerStyleEnum beerStyle, PageRequest pageRequest, Boolean showInventoryOnHand) {
        return null;
    }

    @Cacheable(cacheNames = "beerCache", key = "#beerId", condition = "#showInventoryOnHand == false ")
    @Override
    public Mono<BeerDto> getById(Integer beerId, Boolean showInventoryOnHand) {
        if (showInventoryOnHand) {
            return beerRepository.findById(beerId).map(beerMapper::beerToBeerDtoWithInventory);
        } else {
            return beerRepository.findById(beerId).map(beerMapper::beerToBeerDto);
        }
    }

    @Override
    public Mono<BeerDto> saveNewBeer(BeerDto beerDto) {
//        return beerMapper.beerToBeerDto(beerRepository.save(beerMapper.beerDtoToBeer(beerDto)));
        return null;
    }

    @Override
    public Mono<BeerDto> updateBeer(Integer beerId, BeerDto beerDto) {
        return beerRepository.findById(beerId)
                .defaultIfEmpty(Beer.builder().build())
                .map(beer -> {
             beer.setBeerName(beerDto.getBeerName());
             beer.setBeerStyle(BeerStyleEnum.valueOf(beerDto.getBeerStyle()));
             beer.setPrice(beerDto.getPrice());
             beer.setUpc(beerDto.getUpc());
             return beer;
        }).flatMap(updateBeer -> {
            if (updateBeer.getId() != null) {
                return beerRepository.save(updateBeer);
            }
            return Mono.just(updateBeer);
                })
                 .map(beerMapper::beerToBeerDto);
    }

    @Cacheable(cacheNames = "beerUpcCache")
    @Override
    public Mono<BeerDto> getByUpc(String upc) {
        return beerRepository.findByUpc(upc).map(beerMapper::beerToBeerDto);
    }

    @Override
    public void deleteBeerById(Integer beerId) {
        beerRepository.deleteById(beerId).subscribe();
    }
}
