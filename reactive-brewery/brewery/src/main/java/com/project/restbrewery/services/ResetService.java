package com.project.restbrewery.services;

import com.project.restbrewery.bootstrap.BeerLoader;
import com.project.restbrewery.repositories.BeerRepository;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class ResetService {
    private final BeerRepository beerRepository;
    private final BeerLoader beerLoader;

    @Scheduled(fixedRate = 20000)
    @Transactional
    public void checkBeerCount() {
        resetBeers();
    }
    @Scheduled(fixedRate = 3500000, initialDelay = 5000)
    @SneakyThrows
    @Transactional
    public void resetBeers() {
        log.info("Reset beer data");
        beerRepository.deleteAllInBatch();
        beerRepository.flush();
        beerLoader.run();
    }
}
