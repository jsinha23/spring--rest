package com.demo.customerinfo.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.customerinfo.model.DepartmentsModel;
import com.demo.customerinfo.repository.DepartmentsRepository;

@Service
public class DepartmentsServiceImpl {

	@Autowired
	private DepartmentsRepository departmentRepo;
	
	
	public List<DepartmentsModel> getList(){
		return departmentRepo.findAll();
	}
}
