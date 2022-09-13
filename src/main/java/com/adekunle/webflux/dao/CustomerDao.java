package com.adekunle.webflux.dao;

import com.adekunle.webflux.dto.Customer;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;

import java.time.Duration;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Component
public class CustomerDao {

    public List<Customer> getAllCustomers() throws InterruptedException {

        return IntStream.rangeClosed(1,50)
                .peek(i -> System.out.println("processing count : "+i))
                .peek(this::sleepThread)
                .mapToObj(i -> new Customer(i,i+"customer"))
                .collect(Collectors.toList());
    }

    private void sleepThread(int i) {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {

        }

    }

    public Flux<Customer> getAllCustomersStream(){  // instead of using List , you should use flux when dealing with large data, and Mono for single data

        return Flux.range(1,50)
                .doOnNext(e -> System.out.println(" reactive flux sequence : "+e))
                .delayElements(Duration.ofSeconds(1))
                .map(i -> new Customer(i,i+"customer"));
    }

    public Flux<Customer> getAllCustomerList(){  // instead of using List , you should use flux when dealing with large data, and Mono for single data

        return Flux.range(1,50)
                .doOnNext(e -> System.out.println(" reactive flux sequence : "+e))
                .map(i -> new Customer(i,i+"customer"));
    }
}
