package com.cognizant.spring_learn;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@SpringBootApplication
public class SpringLearnApplication {
	private static final Logger LOGGER= LoggerFactory.getLogger(SpringLearnApplication.class);

	public static void main(String[] args) {

		SpringApplication.run(SpringLearnApplication.class, args);

		//displayDate();

		//displayCountry();

	}

	public static void displayDate(){
		LOGGER.info("START");
		try{
			ApplicationContext context=new ClassPathXmlApplicationContext("date-format.xml");

			SimpleDateFormat format=context.getBean("dateFormat",SimpleDateFormat.class);
			Date date=format.parse("31/12/2018");
			LOGGER.debug("Parsed Date: {}",date);
		}catch(Exception e){
			LOGGER.error("Exception occurred: ",e);
		}

		LOGGER.info("END");
	}
	public static void displayCountry(){
		LOGGER.info("Start");

		try{
			ApplicationContext context=new ClassPathXmlApplicationContext("country.xml");
			// Country country=context.getBean("country",Country.class);
			// Country anotherCountry=context.getBean("country",Country.class);

			List<Country> countries=context.getBean("countryList",List.class);
			// LOGGER.debug("Singleton Scope: {}",country==anotherCountry);

			LOGGER.debug("Countries : {}", countries);
		}catch(Exception e){
			LOGGER.error("Exception occurred: ",e);
		}

		LOGGER.info("End");
	}


}
