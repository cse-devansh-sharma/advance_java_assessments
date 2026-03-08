package com.cap.code.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cap.code.dto.CustomerRequestDTO;
import com.cap.code.dto.CustomerResponseDTO;
import com.cap.code.exception.CustomerNotFoundException;
import com.cap.code.mapper.Mapper;
import com.cap.code.model.Customer;
import com.cap.code.repository.CustomerRepository;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    public CustomerResponseDTO createCustomer(CustomerRequestDTO dto) {
        Customer customer = Mapper.toCustomer(dto);
        Customer saved = customerRepository.save(customer);
        return Mapper.toCustomerResponseDTO(saved);
    }

    public CustomerResponseDTO getCustomerById(long id) {
        Customer customer = customerRepository.findById(id)
                .orElseThrow(() -> new CustomerNotFoundException("Customer not found with id: " + id));
        return Mapper.toCustomerResponseDTO(customer);
    }

    public List<CustomerResponseDTO> getAllCustomers() {
        return customerRepository.findAll().stream().map(c -> Mapper.toCustomerResponseDTO(c)).collect(Collectors.toList());
    }

    public CustomerResponseDTO updateCustomer(long id, CustomerRequestDTO dto) {
        Customer customer = customerRepository.findById(id)
                .orElseThrow(() -> new CustomerNotFoundException("Customer not found with id: " + id));
        customer.setName(dto.getName());
        customer.setEmail(dto.getEmail());
        customer.setPhoneNumber(dto.getPhoneNumber());
        customer.setAddress(dto.getAddress());
        Customer updated = customerRepository.save(customer);
        return Mapper.toCustomerResponseDTO(updated);
    }

    public void deleteCustomer(long id) {
        Customer customer = customerRepository.findById(id)
                .orElseThrow(() -> new CustomerNotFoundException("Customer not found with id: " + id));
        customerRepository.delete(customer);
    }
}