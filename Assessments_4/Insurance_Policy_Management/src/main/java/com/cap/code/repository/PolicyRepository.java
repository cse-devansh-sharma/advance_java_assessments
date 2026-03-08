package com.cap.code.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import com.cap.code.model.Policy;
import com.cap.code.model.PolicyType;

public interface PolicyRepository extends JpaRepository<Policy,Long>{
	
    List<Policy> findByPolicyType(PolicyType policyType);

    List<Policy> findByCustomerId(Long customerId);

    List<Policy> findByPremiumAmountBetween(double minPremium, double maxPremium);

    @Query("SELECT p FROM Policy p WHERE p.customer.email = :email")
    List<Policy> findPoliciesByCustomerEmail(String email);

}
