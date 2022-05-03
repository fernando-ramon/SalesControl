package com.softwarehouse.salescontrol.customer.controller;

import com.softwarehouse.salescontrol.comom.ServiceProcessor;
import com.softwarehouse.salescontrol.customer.domain.Customer;
import com.softwarehouse.salescontrol.customer.domain.Gender;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;


public class CustomerControllerTest {

    @InjectMocks
    CustomerController customerController;

    @Mock
    ServiceProcessor serviceProcessor;

    @BeforeEach
    public void init() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void getAllCustomerTest() {
        Customer customerOne = new Customer(1L, "John", "John", "243.838.020-95", LocalDate.of(1990,2,25), Gender.MALE);
        Customer customerTwo = new Customer(2L, "Alex", "kolenchiski", "201.746.090-78", LocalDate.of(1965,11,2), Gender.MALE);
        Customer customerThree = new Customer(3L, "Steve", "Waugh", "636.884.530-93", LocalDate.of(2001,6,17), Gender.MALE);
        when(serviceProcessor.getAll()).thenReturn(Arrays.asList(customerOne, customerTwo, customerThree));
        List<Customer> customerList = customerController.getAllCustomer().getBody();
        assertEquals(3, customerList.size());
        verify(serviceProcessor, times(1)).getAll();
    }

    @Test
    public void getCustomerByIdTest() {
        Customer customerMock = new Customer(1L, "John", "John", "243.838.020-95", LocalDate.of(1990,2,25), Gender.MALE);
        when(serviceProcessor.getById(1L)).thenReturn(customerMock);
        Customer customer = customerController.getCustomerById(1L).getBody();
        verify(serviceProcessor, times(1)).getById(any());
        assertEquals(customer, customerMock);
    }

    @Test
    public void saveCustomerTest() {
        Customer customerMock = new Customer(1L, "John", "John", "243.838.020-95", LocalDate.of(1990,2,25), Gender.MALE);
        when(serviceProcessor.save(any())).thenReturn(customerMock);
        Customer customer = customerController.insertCustomer(customerMock).getBody();
        verify(serviceProcessor, times(1)).save(any());
        assertEquals(customer, customerMock);
    }

    @Test
    public void updateCustomerTest() {
        Customer customerMock = new Customer(1L, "John", "John", "243.838.020-95", LocalDate.of(1990,2,25), Gender.MALE);
        when(serviceProcessor.update(any())).thenReturn(customerMock);
        Customer customer = customerController.updateCustomer(customerMock).getBody();
        verify(serviceProcessor, times(1)).update(any());
        assertEquals(customer, customerMock);
    }

    @Test
    public void deleteCustomerByIdTest() {
        Customer customerMock = new Customer(1L, "John", "John", "243.838.020-95", LocalDate.of(1990,2,25), Gender.MALE);
        ResponseEntity responseEntity = customerController.deleteCustomerById(1L);
        verify(serviceProcessor, times(1)).deleteById(any());
        assertEquals(responseEntity.getStatusCode(), HttpStatus.NO_CONTENT);
    }
}
