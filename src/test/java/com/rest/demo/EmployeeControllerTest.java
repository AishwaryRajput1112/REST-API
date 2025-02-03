package com.rest.demo;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.rest.demo.controller.EmployeeController;
import com.rest.demo.entity.Employee;
import com.rest.demo.repository.EmployeeRepository;
import com.rest.demo.service.EmployeeService;

@ExtendWith(MockitoExtension.class)
public class EmployeeControllerTest {

	@InjectMocks
	EmployeeController employeeController;

	@Mock
	EmployeeService employeeService;

	EmployeeRepository employeeRepository = mock(EmployeeRepository.class);

	private static Employee empl = new Employee();

	@BeforeAll
	static void test_beforeAll() {
		empl.setEid(101);
		empl.setName("Rajadhiraj Mahakal");
		empl.setAddress("Ujjain/Avantika");
	}

	@AfterAll
	static void test_afterAll() {
		empl = null;
	}

	@Test
	@DisplayName("Success test for Get all employees")
	void test_getAllEmployees_success() {
		List<Employee> employees = new ArrayList<>();
		employees.add(empl);
		when(employeeService.getEmployees()).thenReturn(employees);
		ResponseEntity<List<Employee>> entity = employeeController.getAllEmployees();
		Assertions.assertEquals(String.valueOf("Rajadhiraj Mahakal"), entity.getBody().get(0).getName());
	}

	@ParameterizedTest
	@ValueSource(ints = 101)
	@DisplayName("Success test for Get employees")
	void test_getEmployeeByPathVariable_success(int id) {
		when(employeeService.getEmployee(Mockito.anyInt())).thenReturn(empl);
		ResponseEntity<Employee> employee = employeeController.getEmployeeByPathVariable(String.valueOf(id));
		Assertions.assertEquals(String.valueOf("Rajadhiraj Mahakal"), employee.getBody().getName());
		Assertions.assertEquals(HttpStatus.ACCEPTED, employee.getStatusCode());
	}

	@Test
	@DisplayName("Success test for Save employees")
	void test_saveEmployee_success() {
		ResponseEntity<Employee> employee = employeeController.saveEmployee(empl);
		Assertions.assertEquals(HttpStatus.CREATED, employee.getStatusCode());
	}

	@Test
	@DisplayName("Failure test for Get all employees")
	void test_getAllEmployees_failure() {
		List<Employee> employees = new ArrayList<>();
		employees.add(empl);
		when(employeeService.getEmployees()).thenReturn(employees);
		ResponseEntity<List<Employee>> entity = employeeController.getAllEmployees();
		Assertions.assertNotEquals(String.valueOf("Aishwary"), entity.getBody().get(0).getName());
	}

	@ParameterizedTest
	@ValueSource(ints = 105)
	@DisplayName("failure test for Get employees")
	void test_getEmployeeByPathVariable_failure(int id) {
		when(employeeService.getEmployee(id)).thenReturn(empl);
		ResponseEntity<Employee> employee = employeeController.getEmployeeByPathVariable(String.valueOf(id));
		Assertions.assertNotEquals(String.valueOf("Mahakal"), employee.getBody().getName());
		Assertions.assertNotEquals(HttpStatus.CREATED, employee.getStatusCode());
	}

	@Test
	@DisplayName("failure test for Save employees")
	void test_saveEmployee_failure() {
		ResponseEntity<Employee> emp = employeeController.saveEmployee(empl);
		Assertions.assertNotEquals(HttpStatus.ACCEPTED, emp.getStatusCode());
	}
	
	@Test
	@DisplayName("Success test for delete employees")
	void test_deleteEmployee_failure() {
		ResponseEntity<Employee> emp = employeeController.deleteEmployeeByPathVariable("101");
		Assertions.assertEquals(HttpStatus.OK, emp.getStatusCode());
	}
}
