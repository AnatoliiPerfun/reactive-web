package com.example.reactiveweb;

import com.example.reactiveweb.domain.Person;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.util.List;

import static java.lang.System.out;

class PersonRepositoryImplTest {

    PersonRepositoryImpl personRepository;

    @BeforeEach
    void setUp() {
        personRepository = new PersonRepositoryImpl();
    }

    @Test
    void getByIdBlock() {
        Mono<Person> personMono = personRepository.getById(1);
        Person person = personMono.block();

        assert person != null;
        out.println(person);
    }

    @Test
    void getByIdSubscribe() {
        Mono<Person> personMono = personRepository.getById(1);

        StepVerifier.create(personMono).expectNextCount(1).verifyComplete();

        personMono.subscribe(person -> out.println(person.toString()));
    }

    @Test
    void getByIdMapFunc() {
        Mono<Person> personMono = personRepository.getById(1);

        personMono.map(person -> {
            out.println(person.toString());
           return person.getFirstName();
        }).subscribe(firstName -> out.println("from map: " + firstName));
    }

    @Test
    void fluxTestBlockFirst() {
        Flux<Person> personFlux = personRepository.findAll();
        Person person = personFlux.blockFirst();

        assert person != null;
        out.println(person);
    }

    @Test
    void testFluxSubscribe() {
        Flux<Person> personFlux = personRepository.findAll();

        StepVerifier.create(personFlux).expectNextCount(3).verifyComplete();

        personFlux.subscribe(person -> out.println(person.toString()));
    }

    @Test
    void testFluxToListMono() {
        Flux<Person> personFlux = personRepository.findAll();
        Mono<List<Person>> personListMono = personFlux.collectList();

        personListMono.subscribe(list ->
                list.forEach(person ->
                        out.println(person.toString())
                )
        );
    }

    @Test
    void testFindPersonById() {
        Flux<Person> personFlux = personRepository.findAll();
        final Integer id = 3;
        Mono<Person> personMono = personFlux
                .filter(person -> person.getId().equals(id))
                .next();
        personMono.subscribe(person -> out.println(person.toString()));
    }

    @Test
    void testFindPersonByIdNotFoundWithException() {
        Flux<Person> personFlux = personRepository.findAll();
        final Integer id = 5;
        Mono<Person> personMono = personFlux
                .filter(person -> person.getId().equals(id))
                .single();

        personMono.doOnError(throwable -> out.println("no such ID found"))
                .onErrorReturn(Person.builder().id(id).build())
                .subscribe(person -> out.println(person.toString())
                );
    }

}