package com.adekunle.webflux.controller;

import com.adekunle.webflux.dto.Customer;
import com.adekunle.webflux.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

import java.util.List;

@RestController
@RequestMapping("/api/v1/customer")
public class CustomerController {

    @Autowired
    private CustomerService service;

    @GetMapping("/traditionalREST")
    @ResponseStatus(HttpStatus.OK)
    public List<Customer> getAllCustomers() throws InterruptedException {
        return service.loadAllCustomer();
    }

    @GetMapping(value = "/Reactive", produces = MediaType.TEXT_EVENT_STREAM_VALUE)  // this will crea
    @ResponseStatus(HttpStatus.OK)
    public Flux<Customer> getAllCustomersStream(){
        return service.loadAllCustomerStream();
    }

}
