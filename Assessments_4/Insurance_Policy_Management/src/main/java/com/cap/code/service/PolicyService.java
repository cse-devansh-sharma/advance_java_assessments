package com.cap.code.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.cap.code.dto.*;
import com.cap.code.exception.PolicyNotFoundException;
import com.cap.code.mapper.Mapper;
import com.cap.code.repository.CustomerRepository;
import com.cap.code.repository.PolicyRepository;
import com.cap.code.model.*;

@Service
public class PolicyService {

    @Autowired
    private PolicyRepository policyRepository;

    @Autowired
    private CustomerRepository customerRepository;


    public PolicyResponseDTO createPolicy(PolicyRequestDTO dto) {
        Customer customer = customerRepository.findById(dto.getCustomerId())
                .orElseThrow(() -> new PolicyNotFoundException("Customer not found with id: " + dto.getCustomerId()));
        Policy policy = Mapper.toPolicy(dto);
        policy.setCustomer(customer);
        policy.setStatus(Status.ACTIVE);
        Policy saved = policyRepository.save(policy);
        return Mapper.toPolicyResponseDTO(saved);
    }
    
    public Page<PolicyResponseDTO> getAllPolicies(Pageable pageable) {
        return policyRepository.findAll(pageable)
                .map(p -> Mapper.toPolicyResponseDTO(p));
    }

    public PolicyResponseDTO getPolicyById(long id) {
        Policy policy = policyRepository.findById(id)
                .orElseThrow(() -> new PolicyNotFoundException("Policy not found with id: " + id));
        return Mapper.toPolicyResponseDTO(policy);
    }

    public List<PolicyResponseDTO> getAllPolicies() {
        return policyRepository.findAll()
                .stream()
                .map(p -> Mapper.toPolicyResponseDTO(p))
                .collect(Collectors.toList());
    }

    public PolicyResponseDTO updatePolicy(long id, PolicyRequestDTO dto) {
        Policy policy = policyRepository.findById(id)
                .orElseThrow(() -> new PolicyNotFoundException("Policy not found with id: " + id));

        policy.setPolicyNumber(dto.getPolicyNumber());
        policy.setPolicyType(dto.getPolicyType());
        policy.setPremiumAmount(dto.getPremiumAmount());
        policy.setCoverageAmount(dto.getCoverageAmount());
        policy.setStartDate(dto.getStartDate());
        policy.setEndDate(dto.getEndDate());

        if (dto.getCustomerId() != policy.getCustomer().getId()) {
            Customer newCustomer = customerRepository.findById(dto.getCustomerId())
                    .orElseThrow(() -> new PolicyNotFoundException("Customer not found with id: " + dto.getCustomerId()));
            policy.setCustomer(newCustomer);
        }

        Policy updated = policyRepository.save(policy);
        return Mapper.toPolicyResponseDTO(updated);
    }

    public void deletePolicy(long id) {
        Policy policy = policyRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Policy not found with id: " + id));
        policyRepository.delete(policy);
    }

    public List<PolicyResponseDTO> getPoliciesByType(PolicyType policyType) {
        return policyRepository.findByPolicyType(policyType).stream().map(p -> Mapper.toPolicyResponseDTO(p)).collect(Collectors.toList());
    }

    public List<PolicyResponseDTO> getPoliciesByPremiumRange(double minPremium, double maxPremium) {
        if (minPremium > maxPremium) {
            throw new RuntimeException("minPremium cannot be greater than maxPremium");
        }
        return policyRepository.findByPremiumAmountBetween(minPremium, maxPremium)
                .stream()
                .map(p -> Mapper.toPolicyResponseDTO(p))
                .collect(Collectors.toList());
    }
}