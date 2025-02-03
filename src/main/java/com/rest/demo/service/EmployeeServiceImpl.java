package com.rest.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rest.demo.entity.Employee;
import com.rest.demo.exception.EmployeeNotFoundException;
import com.rest.demo.repository.EmployeeRepository;

import jakarta.transaction.Transactional;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired
	EmployeeRepository employeeRepository;

	@Override
	@Transactional
	public List<Employee> getEmployees() {
		return employeeRepository.findAll();
	}

	@Override
	@Transactional
	public Employee getEmployee(int id) {
		return employeeRepository.findById(id).orElseThrow(()-> new EmployeeNotFoundException());
	}

	@Override
	@Transactional // transaction begins , object created , autocomit=false
	public Employee saveEmployee(Employee e) {//active trsn
		Employee emp =  employeeRepository.save(e);
		return emp;
	}

	@Override
	@Transactional
	public void deleteEmployee(int id) {
		employeeRepository.delete(employeeRepository.findById(id).get());
	}

	@Transactional
	@Override
	public Employee editEmployee(Employee e) {
		Employee emp = employeeRepository.findById(e.getEid()).get();
		emp.setAddress(e.getAddress());
		emp.setName(e.getName());
		emp.setEid(e.getEid());
		return emp;
	}
	
	

}
