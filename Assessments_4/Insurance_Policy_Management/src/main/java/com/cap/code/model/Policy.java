package com.cap.code.model;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Policy {
	
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;
	
	private String policyNumber;
	private PolicyType policyType;
	private double premiumAmount;
	

	private double coverageAmount;
	
	@JsonFormat(pattern = "dd-MM-yyyy")
	private LocalDate startDate;
	
	@JsonFormat(pattern = "dd-MM-yyyy")
	private LocalDate endDate;
	private Status status;
	
	@ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;
	
	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public Policy() {}
	
	public Policy( String policyNumber, PolicyType policyType, double premiumAmount, double coverageAmount,LocalDate startDate, LocalDate endDate, Status status) {
		super();
		this.policyNumber = policyNumber;
		this.policyType = policyType;
		this.premiumAmount = premiumAmount;
		this.coverageAmount = coverageAmount;
		this.startDate = startDate;
		this.endDate = endDate;
		this.status = status;
	}
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
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
	public Status getStatus() {
		return status;
	}
	public void setStatus(Status status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "Policy [id=" + id + ", policyNumber=" + policyNumber + ", policyType=" + policyType + ", premiumAmount="
				+ premiumAmount + ", coverageAmount=" + coverageAmount + ", startDate=" + startDate + ", endDate="
				+ endDate + ", status=" + status + "]";
	}
	
}
