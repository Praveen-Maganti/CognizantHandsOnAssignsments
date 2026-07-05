package com.cognizant.spring_learn.service.impl;

import com.cognizant.spring_learn.Country;
import com.cognizant.spring_learn.exception.CountryNotFoundException;
import com.cognizant.spring_learn.service.CountryService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class CountryServiceImpl implements CountryService {
    private static final Logger LOGGER= LoggerFactory.getLogger(CountryServiceImpl.class);

    public Country getCountry(String code) throws CountryNotFoundException {
        LOGGER.info("Start");
        ApplicationContext context=new ClassPathXmlApplicationContext("country.xml");

        if(!context.containsBean(code)){
            throw new CountryNotFoundException();
        }
        Country country=context.getBean(code,Country.class);

        LOGGER.debug("Country: {}",country);
        LOGGER.info("End");
        return country;
    }

}
