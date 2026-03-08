package com.cap.code.dto;

import java.time.LocalDate;

import com.cap.code.model.Customer;
import com.cap.code.model.PolicyType;
import com.cap.code.model.Status;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public class PolicyRequestDTO {
	
	@NotBlank(message = "Policy number cannot be blank")
	private String policyNumber;
	
	private PolicyType policyType;
	
	@Positive(message = "Premium amount must be positive")
	private double premiumAmount;
	
	@Positive(message = "Coverage amount must be positive")
	private double coverageAmount;
	
	@NotNull(message = "Start date cannot be null")
	
	private LocalDate startDate;
	private LocalDate endDate;
	private long customerId;
	

	public long getCustomerId() {
		return customerId;
	}
	public void setCustomerId(long customerId) {
		this.customerId = customerId;
	}
	public String getPolicyNumber() {
		return policyNumber;
	}
	public void setPolicyNumber(String policyNumber) {
		this.policyNumber = policyNumber;
	}
	public PolicyType getPolicyType() {
		return policyType;
	}
	public void setPolicyType(PolicyType policyType) {
		this.policyType = policyType;
	}
	public double getPremiumAmount() {
		return premiumAmount;
	}
	public void setPremiumAmount(double premiumAmount) {
		this.premiumAmount = premiumAmount;
	}
	public double getCoverageAmount() {
		return coverageAmount;
	}
	public void setCoverageAmount(double coverageAmount) {
		this.coverageAmount = coverageAmount;
	}
	public LocalDate getStartDate() {
		return startDate;
	}
	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}
	public LocalDate getEndDate() {
		return endDate;
	}
	public void setEndDate(LocalDate endDate) {
		this.endDate = endDate;
	}

	
	

}
