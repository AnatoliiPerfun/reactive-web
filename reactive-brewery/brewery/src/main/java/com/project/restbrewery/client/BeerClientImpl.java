package com.project.restbrewery.client;

import com.project.restbrewery.config.WebClient;
import com.project.restbrewery.config.WebClientProperties;
import com.project.restbrewery.web.model.BeerDto;
import com.project.restbrewery.web.model.BeerPagedList;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class BeerClientImpl implements BeerClient {

    public WebClient webClient;
    @Override
    public Mono<BeerDto> getBeerById(UUID id, Boolean showInventoryOnHand) {
        return null;
    }
    @Override
    public Mono<BeerPagedList> listBeers(Integer pageNumber,
                                         Integer pageSize,
                                         String beerName,
                                         String beerStyle,
                                         Boolean showInvenotryOnHand) {
        return webClient.get()
                .uri(uriBuilder -> uriBuilder.path(WebClientProperties.BEER_V1_PATH)
                        .queryParamIfPresent("pageName", Optional.ofNullable(pageNumber))
                        .queryParamIfPresent("pageSize", Optional.ofNullable(pageSize))
                        .queryParamIfPresent("beerName", Optional.ofNullable(beerName))
                        .queryParamIfPresent("beerStyle", Optional.ofNullable(beerStyle))
                        .queryParamIfPresent("showInvenotryOnHand", Optional.ofNullable(showInvenotryOnHand))
                        .build())
                .retrieve()
                .bodyToMono(BeerPagedList.class);
    }
    @Override
    public Mono<ResponseEntity<Void>> createBeer(BeerDto beerDto) {
        return null;
    }
    @Override
    public Mono<ResponseEntity<Void>> updateBeer(UUID Id,BeerDto beerDto) {
        return null;
    }
    @Override
    public Mono<ResponseEntity<Void>> deleteBeerById(Integer id) {
        return null;
    }
    @Override
    public Mono<BeerDto> getBeerByUPC(String upc) {
        return  webClient.get()
                .uri(uriBuilder -> uriBuilder.path(WebClientProperties.BEER_V1_PATH + "/" + upc)
                .build(upc))
                .retrieve()
                .bodyToMono(BeerDto.class);
    }
}
