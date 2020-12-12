package com.demo.customerinfo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.demo.customerinfo.model.CustomerModel;

@Repository
public interface CustomerRepository extends JpaRepository<CustomerModel, Integer>{

	CustomerModel findByRole(String role);
	
	CustomerModel findByCustomerId(Integer customerId);
}
