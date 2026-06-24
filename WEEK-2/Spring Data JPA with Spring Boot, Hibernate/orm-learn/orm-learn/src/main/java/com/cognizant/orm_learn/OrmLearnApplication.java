package com.cognizant.orm_learn;

import com.cognizant.orm_learn.model.Country;
import com.cognizant.orm_learn.service.CountryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import java.util.List;

@SpringBootApplication
public class OrmLearnApplication {

	private static final Logger LOGGER =
			LoggerFactory.getLogger(OrmLearnApplication.class);

	private static CountryService countryService;

	public static void main(String[] args) {

		ApplicationContext context =
				SpringApplication.run(OrmLearnApplication.class, args);

		countryService = context.getBean(CountryService.class);

		//testGetAllCountries();

		testAddCountry();
		getCountryTest();
		testUpdateCountry();
		testDeleteCountry();
		LOGGER.info("Inside main");
	}

	private static void testUpdateCountry() {
		LOGGER.info("Start");
		countryService.updateCountry(
				"NP",
				"Federal Democratic Republic of Nepal"
		);
		try {
			Country country =
					countryService.findCountryByCode("NP");
			LOGGER.debug("Updated Country: {}", country);
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
		}
		LOGGER.info("End");
	}
	private static void testDeleteCountry() {
		LOGGER.info("Start");
		countryService.deleteCountry("NP");
		LOGGER.info("Country Deleted");
		LOGGER.info("End");
	}
	private static void getCountryTest(){
		LOGGER.info("Start");
		try {
			Country country =
					countryService.findCountryByCode("IN");

			LOGGER.debug("Country: {}", country);
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
		}
		LOGGER.info("End");
	}

	private static void testAddCountry() {

		LOGGER.info("Start");

		Country country = new Country();
		country.setCode("NP");
		country.setName("Nepal");

		countryService.addCountry(country);
		try {
			Country addedCountry =
					countryService.findCountryByCode("NP");
			LOGGER.debug("Country Added: {}", addedCountry);

		} catch (Exception e) {
			LOGGER.error(e.getMessage());
		}
		LOGGER.info("End");
	}

	private static void testGetAllCountries() {
		LOGGER.info("Start");

		List<Country> countries = countryService.getAllCountries();

		LOGGER.debug("countries={}", countries);

		LOGGER.info("End");
	}
}