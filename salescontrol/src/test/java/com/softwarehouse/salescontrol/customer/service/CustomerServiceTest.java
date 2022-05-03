package com.softwarehouse.salescontrol.customer.service;

import com.softwarehouse.salescontrol.customer.domain.Customer;
import com.softwarehouse.salescontrol.customer.domain.Gender;
import com.softwarehouse.salescontrol.customer.handler.CustomerNotFoundException;
import com.softwarehouse.salescontrol.customer.repository.CustomerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Sort;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrowsExactly;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class CustomerServiceTest {


    @InjectMocks
    CustomerService customerService;

    @Mock
    CustomerRepository customerRepository;

    @BeforeEach
    public void init() {
        customerService = new CustomerService();
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void getAllCustomerTest() {
        Customer customerOne = new Customer(1L, "John", "John", "243.838.020-95", LocalDate.of(1990,2,25), Gender.MALE);
        Customer customerTwo = new Customer(2L, "Alex", "kolenchiski", "201.746.090-78", LocalDate.of(1965,11,2), Gender.MALE);
        Customer customerThree = new Customer(3L, "Steve", "Waugh", "636.884.530-93", LocalDate.of(2001,6,17), Gender.MALE);
        Sort sort = Sort.by(Sort.Direction.ASC, "firstName", "lastName");
        when(customerRepository.findAll(sort)).thenReturn(Arrays.asList(customerOne, customerTwo, customerThree));
        List<Customer> customerList = customerService.getAll();
        assertEquals(3, customerList.size());
        verify(customerRepository, times(1)).findAll(sort);
    }

    @Test
    public void getCustomerByIdTest() {
        Customer customerMock = new Customer(1L, "John", "John", "243.838.020-95", LocalDate.of(1990,2,25), Gender.MALE);
        Optional<Customer> optionalMock = Optional.of(customerMock);
        when(customerRepository.findById(1L)).thenReturn(optionalMock);
        Customer customer = customerService.getById(1L);
        verify(customerRepository, times(1)).findById(any());
        assertEquals(customer, customerMock);
    }

    @Test
    public void getCustomerByIdNotFoundExceptionTest() {
        assertThrowsExactly(CustomerNotFoundException.class, () -> customerService.getById(1L));
    }

    @Test
    public void saveCustomerTest() {
        Customer customerMock = new Customer(1L, "John", "John", "243.838.020-95", LocalDate.of(1990,2,25), Gender.MALE);
        customerMock.setId(null);
        when(customerRepository.save(any())).thenReturn(customerMock);
        Customer customer = customerService.save(customerMock);
        verify(customerRepository, times(1)).save(any());
        assertEquals(customer, customerMock);
    }

    @Test
    public void updateCustomerTest() {
        Customer customerMock = new Customer(1L, "John", "John", "243.838.020-95", LocalDate.of(1990,2,25), Gender.MALE);
        when(customerRepository.save(any())).thenReturn(customerMock);
        Customer customer = customerService.update(customerMock);
        verify(customerRepository, times(1)).save(any());
        assertEquals(customer, customerMock);
    }

    @Test
    public void deleteCustomerByIdCustomerNotFoundTest() {
        assertThrowsExactly(CustomerNotFoundException.class, () -> customerService.deleteById(1L));
    }

    @Test
    public void deleteCustomerByIdTest() {
        when(customerRepository.findById(1L)).thenReturn(Optional.of(new Customer(1L, "John", "John", "243.838.020-95", LocalDate.of(1990,2,25), Gender.MALE)));
        customerService.deleteById(1L);
        verify(customerRepository, times(1)).delete(any());
    }

}
