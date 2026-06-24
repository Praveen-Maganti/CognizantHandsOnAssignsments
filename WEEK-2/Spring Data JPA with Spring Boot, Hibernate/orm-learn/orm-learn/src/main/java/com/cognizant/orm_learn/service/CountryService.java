package com.cognizant.orm_learn.service;

import com.cognizant.orm_learn.model.Country;
import com.cognizant.orm_learn.repository.CountryRepository;
import com.cognizant.orm_learn.service.exception.countryNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CountryService {
    @Autowired
    private CountryRepository countryrepository;

    @Transactional
    public List<Country> getAllCountries(){
        return countryrepository.findAll();
    }

    @Transactional
    public Country findCountryByCode(String countryCode) throws countryNotFoundException{
        Optional<Country> result = countryrepository.findById(countryCode);
        if (!result.isPresent()) {
            throw new countryNotFoundException(
                    "Country with code " + countryCode + " not found");
        }

        Country country = result.get();
        return country;
    }
    @Transactional
    public void addCountry(Country country) {
        countryrepository.save(country);
    }

    @Transactional
    public void updateCountry(String code, String name) {

        Country country =
                countryrepository.getReferenceById(code);
        country.setName(name);
        countryrepository.save(country);
    }

    @Transactional
    public void deleteCountry(String code) {
        countryrepository.deleteById(code);
    }
}
