package com.cap.code.controller;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cap.code.dto.PolicyRequestDTO;
import com.cap.code.dto.PolicyResponseDTO;
import com.cap.code.model.PolicyType;
import com.cap.code.service.PolicyService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/policies")
public class PolicyController {

    @Autowired
    private PolicyService policyService;
    
    
    @PostMapping
    public ResponseEntity<PolicyResponseDTO> createPolicy(
            @Valid @RequestBody PolicyRequestDTO dto) {
        PolicyResponseDTO response = policyService.createPolicy(dto);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

 
 @GetMapping
 public ResponseEntity<?> getAllPolicies(
         @RequestParam(required = false) Integer page,
         @RequestParam(required = false) Integer size,
         @RequestParam(defaultValue = "id") String sortBy,
         @RequestParam(defaultValue = "asc") String direction) {

     if (page != null && size != null) {
         Sort sort = direction.equalsIgnoreCase("desc")
                 ? Sort.by(sortBy).descending()
                 : Sort.by(sortBy).ascending();
         Pageable pageable = PageRequest.of(page, size, sort);
         return ResponseEntity.ok(policyService.getAllPolicies(pageable));
     }

     return ResponseEntity.ok(policyService.getAllPolicies());
 }


    @GetMapping("/{id}")
    public ResponseEntity<PolicyResponseDTO> getPolicyById(@PathVariable long id) {
        PolicyResponseDTO response = policyService.getPolicyById(id);
        return ResponseEntity.ok(response);
    }


    @PutMapping("/{id}")
    public ResponseEntity<PolicyResponseDTO> updatePolicy(
            @PathVariable long id,
            @Valid @RequestBody PolicyRequestDTO dto) {
        PolicyResponseDTO response = policyService.updatePolicy(id, dto);
        return ResponseEntity.ok(response);
    }



    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletePolicy(@PathVariable long id) {
        policyService.deletePolicy(id);
        return ResponseEntity.ok("Policy deleted successfully");
    }


    @GetMapping("/type/{type}")
    public ResponseEntity<List<PolicyResponseDTO>> getPoliciesByType(
            @PathVariable PolicyType type) {
        List<PolicyResponseDTO> response = policyService.getPoliciesByType(type);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/premium")
    public ResponseEntity<List<PolicyResponseDTO>> getPoliciesByPremiumRange(
            @RequestParam double min,
            @RequestParam double max) {
        List<PolicyResponseDTO> response = policyService.getPoliciesByPremiumRange(min, max);
        return ResponseEntity.ok(response);
    }
}