package com.demo.customerinfo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.demo.customerinfo.model.DepartmentsModel;

@Repository
public interface DepartmentsRepository extends JpaRepository<DepartmentsModel, Integer>{

}
