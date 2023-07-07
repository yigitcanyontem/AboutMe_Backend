package com.yigitcanyontem.aboutme.country;

import com.yigitcanyontem.aboutme.exceptions.SearchNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CountryService {

    private final CountryRepository countryRepository;

    public CountryService(CountryRepository countryRepository) {
        this.countryRepository = countryRepository;
    }


    public Country singleCountry(Integer id){
        return countryRepository.findById(id).orElseThrow(() -> new SearchNotFoundException("Country With id " +id +" doesn't exist"));
    }
    public List<Country> allCountries(){
        return countryRepository.findAll();
    }
}
