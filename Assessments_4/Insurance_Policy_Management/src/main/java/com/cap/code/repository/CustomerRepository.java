package com.cap.code.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cap.code.model.Customer;

public interface CustomerRepository extends JpaRepository<Customer,Long>{

}
