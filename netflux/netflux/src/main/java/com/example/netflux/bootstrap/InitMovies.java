package com.example.netflux.bootstrap;


import com.example.netflux.domain.Movie;
import com.example.netflux.repository.MovieRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;

import static java.lang.System.out;

@RequiredArgsConstructor
@Component
public class InitMovies implements CommandLineRunner {

    private final MovieRepository movieRepository;

    @Override
    public void run(String... args) {
        movieRepository.deleteAll()
                .thenMany(
                        Flux.just("Movie1", "Movie2", "another movie", "movie3", "new")
                                .map(Movie::new)
                .flatMap(movieRepository::save))
                                .subscribe(null, null, () -> movieRepository.findAll()
                                        .subscribe(out::println)
                );
    }
}
