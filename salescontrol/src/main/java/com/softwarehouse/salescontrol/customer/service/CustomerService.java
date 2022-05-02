package com.softwarehouse.salescontrol.customer.service;

import com.softwarehouse.salescontrol.comom.ServiceProcessor;
import com.softwarehouse.salescontrol.customer.domain.Customer;
import com.softwarehouse.salescontrol.customer.handler.CustomerInvalidException;
import com.softwarehouse.salescontrol.customer.handler.CustomerNotFoundException;
import com.softwarehouse.salescontrol.customer.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Primary
@Service
public class CustomerService implements ServiceProcessor<Customer> {

    @Autowired
    private CustomerRepository repository;

    @Override
    public List<Customer> getAll() {
        return repository.findAll(Sort.by(Sort.Direction.ASC, "firstName", "lastName"));
    }

    @Override
    public Customer get(Long id) {
        return repository.findById(id).orElseThrow(CustomerNotFoundException::new);
    }

    @Override
    public Customer save(Customer entity) {
        if (entity.getId() != null) {
            throw new CustomerInvalidException("Id must be null to insert a new customer.");
        }
        return repository.save(entity);
    }

    @Override
    public Customer update(Customer entity) {
        if (entity.getId() == null) {
            throw new CustomerInvalidException("Id must be informed to update a customer.");
        }
        return repository.save(entity);
    }

    @Override
    public void delete(Long id) {
        Customer customer = repository.findById(id).orElseThrow(CustomerNotFoundException::new);
        repository.delete(customer);
    }
}
