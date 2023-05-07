package com.yigitcanyontem.aboutme.country;

import com.yigitcanyontem.aboutme.country.Country;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CountryRepository extends JpaRepository<Country,Integer> {
}
