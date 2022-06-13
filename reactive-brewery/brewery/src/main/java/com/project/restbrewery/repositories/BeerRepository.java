package com.project.restbrewery.repositories;


import com.project.restbrewery.domain.Beer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


public interface BeerRepository extends ReactiveCrudRepository<Beer, Integer> {

//    Page<Beer> findAllByBeerStyle(BeerStyleEnum beerStyle, Pageable pageable);
//    Page<Beer> findAllByBeerNameAndBeerStyle(String beerName, BeerStyleEnum beerStyle, Pageable pageable);
    Flux<Page<Beer>> findAllByBeerName(String beerName, Pageable pageable);
    Mono<Beer> findByUpc(String upc);
    void deleteAllInBatch();
    void flush();
}
