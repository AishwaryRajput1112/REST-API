package com.rest.demo.service;

import java.util.List;

import com.rest.demo.entity.Employee;

public interface EmployeeService {

	public List<Employee> getEmployees();
	
	public Employee getEmployee(int id);
	
	public Employee saveEmployee(Employee e);
	
	public void deleteEmployee(int id);
	
	public Employee editEmployee(Employee e);
}
