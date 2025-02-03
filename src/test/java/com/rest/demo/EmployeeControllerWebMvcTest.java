package com.rest.demo;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.rest.demo.controller.EmployeeController;

@WebMvcTest(controllers = EmployeeController.class)
public class EmployeeControllerWebMvcTest {
	
	@Autowired MockMvc mockMvc;
	
	String json = 	"""
			   			{ 
			   				"eid": 101,
			    			"name": "Bholenath",
			    			"address": "Yatra tatra sarvatra Shiva"
			    		}
					""";
	
	/*
	 * .accept(MediaType.APPLICATION_JSON) .content(json.getBytes())
	 * .contentType(MediaType.APPLICATION_JSON))
	 */

//	@Test
	void test_getAllEmployees_success() throws Exception {
		mockMvc.perform(
				MockMvcRequestBuilders
				.get("http://localhost:8080/api/v1/getEmployees")
				.accept(MediaType.APPLICATION_JSON))
		.andDo(print());
		/*
		 * .andExpect(status().isAccepted()) ;
		 */
	}
}
