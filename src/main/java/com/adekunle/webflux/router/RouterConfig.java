package com.adekunle.webflux.router;

import com.adekunle.webflux.dto.Customer;
import com.adekunle.webflux.handler.CustomerHandler;
import com.adekunle.webflux.handler.CustomerHandlerStream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

@Configuration
public class RouterConfig {

    @Autowired
    private CustomerHandler handler;

    @Autowired
    private CustomerHandlerStream streamHandler;

    @Bean
    public RouterFunction<ServerResponse> routerFunction(){
        return (RouterFunction<ServerResponse>) RouterFunctions.route()
                .GET("/router/customer",handler::loadAllCustomer)
                .GET("/router/customerstream", streamHandler::loadAllCustomerStream)
                .build();
    }
}
