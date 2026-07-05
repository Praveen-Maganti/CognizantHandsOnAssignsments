package com.congnizant.Employee;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.congnizant.Employee.model.Employee;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class EmployeeApplicationTests {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private ObjectMapper objectMapper;

	@Test
	void contextLoads() {
	}

	@Test
	void testUpdateEmployeeValidationFailure() throws Exception {

		Employee employee = new Employee();

		employee.setId(1);
		employee.setName("");      // Invalid
		employee.setSalary(-1000); // Invalid
		employee.setPermanent(true);

		mockMvc.perform(put("/employees")
						.contentType(MediaType.APPLICATION_JSON)
						.content(objectMapper.writeValueAsString(employee)))
				.andExpect(status().isBadRequest());
	}

	@Test
	void testUpdateEmployeeNotFound() throws Exception {

		String json = """
    {
      "id":999,
      "name":"John",
      "salary":50000,
      "permanent":true,
      "department":{
        "id":1,
        "name":"IT"
      },
      "skillList":[
        {
          "id":1,
          "name":"Java"
        }
      ]
    }
    """;

		mockMvc.perform(put("/employees")
						.contentType(MediaType.APPLICATION_JSON)
						.content(json))
				.andExpect(status().isNotFound());
	}
}