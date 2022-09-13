package com.adekunle.webflux.service;

import com.adekunle.webflux.dao.CustomerDao;
import com.adekunle.webflux.dto.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

import java.util.List;

@Service
public class CustomerService {

    @Autowired
    private CustomerDao customerDao;

    public List<Customer> loadAllCustomer() throws InterruptedException {
        long start = System.currentTimeMillis();
        List<Customer> customerList = customerDao.getAllCustomers();
        long end = System.currentTimeMillis();
        System.out.println("total execution time : " + (end -start));

        return customerList;
    }

    public Flux<Customer> loadAllCustomerStream(){
        long start = System.currentTimeMillis();
        Flux<Customer> customerList = customerDao.getAllCustomersStream();
        long end = System.currentTimeMillis();
        System.out.println("total execution time : " + (end -start));

        return customerList;
    }
}
