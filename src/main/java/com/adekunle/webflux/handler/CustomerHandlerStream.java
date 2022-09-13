package com.adekunle.webflux.handler;

import com.adekunle.webflux.dao.CustomerDao;
import com.adekunle.webflux.dto.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class CustomerHandlerStream {

    @Autowired
    private CustomerDao dao;
    public Mono<ServerResponse> loadAllCustomerStream(ServerRequest request){
        Flux<Customer> customerStream = dao.getAllCustomersStream();
        return ServerResponse.ok().body(customerStream,Customer.class);
    }
}
