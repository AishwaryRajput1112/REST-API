/**
 * 
 */
package com.rest.demo.controller;

import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.rest.demo.entity.Employee;
import com.rest.demo.exception.EmployeeNotFoundException;
import com.rest.demo.exception.NoRequestBodyException;
import com.rest.demo.service.EmployeeService;
import com.rest.demo.utility.GetStaticEmployee;

/**
 * https://stackoverflow.com/questions/32367501/
 * what-is-the-difference-between-pathparam-and-pathvariable
 * #:~:text=%40PathVariable%20and%20%40PathParam%20both%20are%20used%20
 * for%20accessing,Spring%20so%20it%20works%20in%20MVC%20and%20REST.
 *
 */
@Controller
public class EmployeeController {

	@Autowired
	EmployeeService employeeService;

	Logger logger = Logger.getLogger("RestApiApplicationController");

	/*------------http://localhost:8080/application/getEmployees-------------------------*/
	@GetMapping(value = "/getEmployees", produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<List<Employee>> getAllEmployees() {
		logger.info("Inside /getEmployees");
		return new ResponseEntity(employeeService.getEmployees(), HttpStatus.ACCEPTED);
	}

	/*------------http://localhost:8080/application/getEmployee/5-------------------------*/
	@GetMapping(value = "/getEmployee/{id}", produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<Employee> getEmployeeByPathVariable(@PathVariable("id") String id) {
		if (id == null) {
			throw new EmployeeNotFoundException();
		}
		logger.info("Inside /getEmployee/{id} with path variable = " + id);
		return new ResponseEntity(employeeService.getEmployee(Integer.valueOf(id)), HttpStatus.ACCEPTED);
	}

	/*------------http://localhost:8080/application/getEmployee?name=ShivDas----------------- */
	@GetMapping(value = "/getEmployee", produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<Employee> getEmployeeByRequestParam(
			@RequestParam(value = "name", defaultValue = "Shiv Bhakt") String name) {
		logger.info("Inside /getEmployeeByRequestParam with request param = " + name);
		return new ResponseEntity(GetStaticEmployee.getEmployee(), HttpStatus.ACCEPTED);
	}

	/*------------http://localhost:8080/application/saveEmployee------------------------------ */
	/*
	 * { "eid":102, "name":"Aishwary Shivbhakt", "address":"Avantika" }
	 * content-type:application/json
	 */
	@PostMapping(value = "/saveEmployee", produces = { MediaType.APPLICATION_JSON_VALUE }, consumes = {
			MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<Employee> saveEmployee(@RequestBody Employee employee) {
		logger.info("Inside /saveEmployees");
		logger.info("Request body - " + employee);
		return employee != null ? new ResponseEntity(employeeService.saveEmployee(employee), HttpStatus.CREATED) : null;
	}

	/**
	 * http://localhost:8080/application/deleteEmployee/52
	 */
	@PutMapping(value = "/editEmployee", produces = { MediaType.APPLICATION_JSON_VALUE }, consumes = {
			MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<Employee> editEmployee(@RequestBody Employee employee) {
		if(employee==null) {
			throw new NoRequestBodyException();
		}
		logger.info("Inside /editEmployee");
		logger.info("Request body - " + employee);
		Employee updatedEmployee = employeeService.editEmployee(employee);
		return employee != null ? new ResponseEntity(updatedEmployee, HttpStatus.ACCEPTED) : null;
	}

	/**
	 * http://localhost:8080/application/editEmployee-------------------------- {
	 * "eid":"1", "name":"Shiva", "address":"Bramhand" }
	 */
	@DeleteMapping(value = "/deleteEmployee/{id}", produces = { MediaType.APPLICATION_JSON_VALUE }, consumes = {
			MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<Employee> deleteEmployeeByPathVariable(@PathVariable("id") String id) {
		if (id == null) {
			throw new EmployeeNotFoundException();
		}
		logger.info("Inside /deleteEmployee");
		employeeService.deleteEmployee(Integer.parseInt(id));
		return new ResponseEntity("Deleted !!", HttpStatus.OK);
	}
}
