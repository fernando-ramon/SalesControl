package com.softwarehouse.salescontrol.customer.controller;

import com.softwarehouse.salescontrol.comom.ServiceProcessor;
import com.softwarehouse.salescontrol.customer.domain.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("v1")
public class CustomerController {

    @Autowired
    private ServiceProcessor<Customer> service;

    @GetMapping("/customer")
    public ResponseEntity<List<Customer>> getAllCustomer() {
        return new ResponseEntity<>(service.getAll(), HttpStatus.OK);
    }

    @GetMapping("/customer/{id}")
    public ResponseEntity<Customer> getCustomerById(@PathVariable Long id) {
        return new ResponseEntity<>(service.getById(id), HttpStatus.OK);
    }

    @PostMapping("/customer")
    public ResponseEntity<Customer> insertCustomer(@Valid @RequestBody Customer customer) {
        return new ResponseEntity<>(service.save(customer), HttpStatus.OK);
    }

    @PutMapping("/customer")
    public ResponseEntity<Customer> updateCustomer(@Valid @RequestBody Customer customer) {
        return new ResponseEntity<>(service.update(customer), HttpStatus.OK);
    }

    @DeleteMapping("/customer/{id}")
    public ResponseEntity deleteCustomerById(@PathVariable Long id) {
        service.deleteById(id);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

}
