package com.adekunle.webflux;

import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class MonoFluxTest {

    @Test
    public void testMono(){
       Mono<String> monoString =  Mono.just("HELLO_WORLD");
       // Mono<String> monoString =  Mono.just("HELLO_WORLD").log();  // if you want to see the log of events you can add the log()
       monoString.subscribe(System.out::println);
    }

    @Test
    public void testMonoWithError(){
        Mono<?> monoString =  Mono.just("HELLO_WORLD")
                .then(Mono.error(new RuntimeException("Exceptions has occurred")))
                .log();
        monoString.subscribe(System.out::println);
    }

    @Test
    public void fluxTest(){
       // Flux<String> fluxString = Flux.just("Reactive Java","Spring-boot","Microservices","java","Postgres","Reactive-Mongo","Hibernate","Spring-Security");
        Flux<String> fluxString = Flux
                .just("Reactive Java","Spring-boot","Microservices","java","Postgres","Reactive-MongoDB","Hibernate","Spring-Security")
                .log();
        fluxString.subscribe(System.out::print);
    }

    @Test
    public void fluxTestConcat(){
        Flux<String> fluxString = Flux.just("Reactive Java","Spring-boot","Microservices","java","Postgres","Reactive-Mongo")
                .concatWithValues("AWS")
                .log();

        fluxString.subscribe(System.out::print,e -> System.out.println(e.getMessage()));
    }
    @Test
    public void fluxTestWithError(){
        Flux<String> fluxString = Flux.just("Reactive Java","Spring-boot","Microservices","java","Postgres","Reactive-Mongo")
                .concatWithValues("AWS")
                .concatWith(Flux.error(new RuntimeException("ERROR in flux has occurred")))
                .log();

        fluxString.subscribe(System.out::print,e -> System.out.println(e.getMessage()));
    }
}
