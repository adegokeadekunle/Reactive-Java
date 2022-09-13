package com.adekunle.webflux.handler;

import com.adekunle.webflux.dao.CustomerDao;
import com.adekunle.webflux.dto.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
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
        return ServerResponse.ok()
                .contentType(MediaType.TEXT_EVENT_STREAM)  // this will make the data come as a stream of data
                .body(customerStream,Customer.class);
    }

    //using request path variable to fetch data
    public Mono<ServerResponse> getCustomerById(ServerRequest request){
        int customerId = Integer.parseInt(request.pathVariable("input")); // get the path-variable method or which ever method needed from the request
        Mono<Customer> customer = dao.getAllCustomersStream().filter(cus -> cus.getId() == customerId).next();
       // Mono<Customer> customer = dao.getAllCustomersStream().filter(cus -> cus.getId() == customerId).take(1).single();  // this also woks same as the above
        return ServerResponse.ok()
                .contentType(MediaType.TEXT_EVENT_STREAM)  // this will make the data come as a stream of data
                .body(customer,Customer.class);
    }

    public Mono<ServerResponse> saveCustomer(ServerRequest request){
       Mono<Customer> customer = request.bodyToMono(Customer.class);
       Mono<String> saveResponse  = customer.map(dto -> dto.getName()+ " "+dto.getId());
       return ServerResponse.ok().body(saveResponse,String.class);
    }

}
