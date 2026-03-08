package com.cap.code.mapper;

import com.cap.code.dto.*;
import com.cap.code.model.*;

public class Mapper {
	
	public static Customer toCustomer(CustomerRequestDTO customerRequest) {
		return new  Customer(customerRequest.getName(),customerRequest.getEmail(),customerRequest.getPhoneNumber(),customerRequest.getAddress());
	}
	

	    public static CustomerResponseDTO toCustomerResponseDTO(Customer customer) {

	        if (customer == null) {
	            return null;
	        }
	        CustomerResponseDTO dto = new CustomerResponseDTO();

	        dto.setId(customer.getId());
	        dto.setName(customer.getName());
	        dto.setEmail(customer.getEmail());
	        dto.setPhoneNumber(customer.getPhoneNumber());
	        dto.setAddress(customer.getAddress());
	        return dto;
	    }
	
	
	public static Policy toPolicy(PolicyRequestDTO policyRequestDTO) {
		return new  Policy(policyRequestDTO.getPolicyNumber(),policyRequestDTO.getPolicyType(),policyRequestDTO.getPremiumAmount(),policyRequestDTO.getCoverageAmount(),policyRequestDTO.getStartDate(),policyRequestDTO.getEndDate(),Status.ACTIVE);
	}
	

	    public static PolicyResponseDTO toPolicyResponseDTO(Policy policy) {

	        if (policy == null) {
	            return null;
	        }
	        PolicyResponseDTO dto = new PolicyResponseDTO();

	        dto.setId(policy.getId());
	        dto.setPolicyNumber(policy.getPolicyNumber());
	        dto.setPolicyType(policy.getPolicyType().name());
	        dto.setPremiumAmount(policy.getPremiumAmount());
	        dto.setCoverageAmount(policy.getCoverageAmount());
	        dto.setStartDate(policy.getStartDate());
	        dto.setEndDate(policy.getEndDate());
	        dto.setStatus(policy.getStatus().name());
	        dto.setCustomer(Mapper.toCustomerResponseDTO(policy.getCustomer()));
	        return dto;
	    }
	}
