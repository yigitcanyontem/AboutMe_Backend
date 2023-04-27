package com.yigitcanyontem.aboutme.repository;

import com.yigitcanyontem.aboutme.entities.Country;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CountryRepository extends JpaRepository<Country,Integer> {
}
