package com.yigitcanyontem.aboutme.country;

import com.yigitcanyontem.aboutme.country.Country;
import com.yigitcanyontem.aboutme.country.CountryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CountryService {

    @Autowired
    public CountryRepository countryRepository;


    public Country singleCountry(Integer id){
        return countryRepository.findById(id).orElseThrow();
    }
    public List<Country> allCountries(){
        return countryRepository.findAll();
    }
}
