package com.softwarehouse.salescontrol.customer.repository;

import com.softwarehouse.salescontrol.customer.domain.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
}
