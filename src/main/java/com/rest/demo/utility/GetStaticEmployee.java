package com.rest.demo.utility;

import com.rest.demo.entity.Employee;

public class GetStaticEmployee {

	public GetStaticEmployee() {
		// TODO Auto-generated constructor stub
	}
	
	public static Employee getEmployee() {
		Employee employee = new Employee();
		employee.setEid(101);
		employee.setName("Rajadhiraj Mahakal");
		employee.setAddress("Ujjain/Avantika");
		return employee;
	}

}
