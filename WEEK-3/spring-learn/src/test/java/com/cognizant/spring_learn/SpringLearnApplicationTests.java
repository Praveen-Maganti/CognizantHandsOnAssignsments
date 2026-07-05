package com.cognizant.spring_learn;

import com.cognizant.spring_learn.controller.CountryController;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class SpringLearnApplicationTests {

	@Autowired
	private CountryController countryController;

	@Autowired
	private MockMvc mvc;

	@Test
	void contextLoads() {
		assertNotNull(countryController);
	}

	@Test
	void testGetCountry() throws Exception {

		mvc.perform(get("/country"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.code").value("IN"))
				.andExpect(jsonPath("$.name").value("India"));
	}

	@Test
	void testCountryException() throws Exception {

		mvc.perform(get("/countries/az"))
				.andExpect(status().isNotFound())
				.andExpect(status().reason("Country not found"));
	}
}

