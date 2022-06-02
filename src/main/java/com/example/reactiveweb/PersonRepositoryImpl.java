package com.example.reactiveweb;

import com.example.reactiveweb.domain.Person;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class PersonRepositoryImpl implements PersonRepository {

    Person joe = new Person(1, "Joe", "Bolton");
    Person luck = new Person(2, "Luck", "Watson");
    Person sam = new Person(3, "Sam", "Huston");

    @Override
    public Mono<Person> getById(Integer id) {
        return Mono.just(joe);
    }

    @Override
    public Flux<Person> findAll() {
        return Flux.just(joe, luck, sam);
    }
}
