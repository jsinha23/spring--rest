package com.demo.customerinfo.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.customerinfo.model.CustomerModel;
import com.demo.customerinfo.repository.CustomerRepository;

@Service
public class CustomerServiceImpl {

	@Autowired
	private CustomerRepository customerRepo;

	public CustomerModel save(CustomerModel customer) {
		return customerRepo.save(customer);
	}

	public CustomerModel findByRoll(String roll) {
		return customerRepo.findByRole(roll);
	}

	public CustomerModel findByCustomerId(Integer customerId) {
		return customerRepo.findByCustomerId(customerId);
	}

	public List<CustomerModel> getList() {
		return customerRepo.findAll();
	}

	public void deleteByCustomerId(Integer customerId) {
		CustomerModel customer = customerRepo.findByCustomerId(customerId);
		customerRepo.delete(customer);
	}
}
