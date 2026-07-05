package com.cognizant.spring_learn.controller;

import com.cognizant.spring_learn.Country;
import com.cognizant.spring_learn.exception.CountryNotFoundException;
import com.cognizant.spring_learn.service.CountryService;
import jakarta.validation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@RestController
public class CountryController {

    @Autowired
    private CountryService countryService;
    private static final Logger LOGGER= LoggerFactory.getLogger(CountryController.class);

    @RequestMapping("/country")
    public Country getCountryIndia(){
        LOGGER.info("Start");

        ApplicationContext context=new ClassPathXmlApplicationContext("country.xml");

        Country country=context.getBean("country",Country.class);
        LOGGER.debug("Country: {}",country);

        LOGGER.info("End");
        return country;
    }

    @GetMapping("/countries")
    public List<Country> getAllCountries(){
        LOGGER.info("Start");
        ApplicationContext context=new ClassPathXmlApplicationContext("country.xml");

        List<Country> countries=context.getBean("countryList",List.class);
        LOGGER.debug("Countries: {}",countries);

        LOGGER.info("End");
        return countries;
    }

    @GetMapping("/countries/{code}")
    public Country getCountry(@PathVariable("code") String code) throws CountryNotFoundException {
        return countryService.getCountry(code);
    }

    @PostMapping("/countries")
    public Country addCountry(@RequestBody @Valid Country country){
        LOGGER.info("Start");
        LOGGER.debug("Country: {}",country);

        /*ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();

        Set<ConstraintViolation<Country>> violations = validator.validate(country);

        List<String> errors = new ArrayList<>();

        for (ConstraintViolation<Country> violation : violations) {
            errors.add(violation.getMessage());
        }

        if (!violations.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, errors.toString());
        }*/

        return country;
    }

}
