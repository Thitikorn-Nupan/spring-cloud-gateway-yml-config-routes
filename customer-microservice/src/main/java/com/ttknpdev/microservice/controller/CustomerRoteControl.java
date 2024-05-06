package com.ttknpdev.microservice.controller;

import com.ttknpdev.microservice.entity.Customer;
import com.ttknpdev.microservice.logging.LogBack;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.ttknpdev.microservice.repository.ServiceRepository;
import com.ttknpdev.microservice.service.CustomerService;

import java.util.List;

@RestController
@RequestMapping(value = "/api/customer")
public class CustomerRoteControl {
    private ServiceRepository<Customer> customerRepository;

    public CustomerRoteControl() {
        customerRepository = new CustomerService();
        LogBack.setLog(CustomerRoteControl.class);
    }

    @GetMapping({"/","/test","/t"})
    ResponseEntity<String> test() {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body("hello world");
    }

    @GetMapping(value = "/reads")
    ResponseEntity<List<Customer>> reads() {
        LogBack.log.info("you requested 8081/api/customer/reads");
        return ResponseEntity
                .status(HttpStatus.ACCEPTED)
                .body(customerRepository.findAll());
    }

    @GetMapping(value = "/read")
    ResponseEntity<Customer> read(@RequestParam Long pk) {
        LogBack.log.info("you requested 8081/api/customer/read?pk={}",pk);
        return ResponseEntity
                .status(HttpStatusCode.valueOf(202))
                .body(customerRepository.findById(pk));
    }

    @PostMapping(value = "/create")
    ResponseEntity<Boolean> create(@RequestBody Customer customer) {
        LogBack.log.info("you requested 8081/api/customer/create and pass json body {}",customer);
        return ResponseEntity
                .status(HttpStatusCode.valueOf(201))
                .body(customerRepository.save(customer));
    }

    @PutMapping(value = "/update")
    ResponseEntity<Boolean> create(@RequestBody Customer customer,@RequestParam Long pk) {
        return ResponseEntity
                .status(HttpStatusCode.valueOf(202))
                .body(customerRepository.edit(customer,pk));
    }

    @DeleteMapping(value = "/delete")
    ResponseEntity<Boolean> create(@RequestParam Long pk) {
        return ResponseEntity
                .status(HttpStatusCode.valueOf(202))
                .body(customerRepository.remove(pk));
    }
}
