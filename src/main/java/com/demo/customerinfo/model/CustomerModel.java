package com.demo.customerinfo.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "customers", uniqueConstraints = {@UniqueConstraint(columnNames = "role")})
public class CustomerModel {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "customer_id")
	private Integer customerId;
	@NotNull(message = "Customer name can 't be empty")
	private String name;
//	@NotNull(message = "Customer role can 't be empty")
	@Column(name = "role",unique = true)
	private String role;
	private String city;
	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "department_id")
	private DepartmentsModel department;

	public CustomerModel() {
	}

	public Integer getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Integer customerId) {
		this.customerId = customerId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public DepartmentsModel getDepartment() {
		return department;
	}

	public void setDepartment(DepartmentsModel department) {
		this.department = department;
	}

	@Override
	public String toString() {
		return "CustomerModel [customerId=" + customerId + ", name=" + name + ", role=" + role + ", city=" + city
				+ ", department=" + department + "]";
	}

	

}
