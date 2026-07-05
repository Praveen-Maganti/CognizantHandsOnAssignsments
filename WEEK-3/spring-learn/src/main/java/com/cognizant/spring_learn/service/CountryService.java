package com.cognizant.spring_learn.service;

import com.cognizant.spring_learn.Country;
import com.cognizant.spring_learn.exception.CountryNotFoundException;

public interface CountryService {

    public Country getCountry(String code) throws CountryNotFoundException;
}
