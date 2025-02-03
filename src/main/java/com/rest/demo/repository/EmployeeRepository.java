package com.rest.demo.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.rest.demo.entity.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
	
	@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT)
	Employee save(Employee e);
	
	@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT)
	Optional<Employee> findById(Integer id);
}
