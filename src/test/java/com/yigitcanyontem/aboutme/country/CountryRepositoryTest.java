package com.yigitcanyontem.aboutme.country;

import com.yigitcanyontem.aboutme.AbstractTestcontainer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Locale;

import static org.assertj.core.api.Assertions.assertThat;
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class CountryRepositoryTest extends AbstractTestcontainer {
    @Autowired
    private CountryRepository countryRepository;
    @BeforeEach
    void setUp() {
        Locale.setDefault(Locale.US);
    }
    @Test
    void getCountry() {
        Country country = countryRepository.findById(1).get();

        assertThat(country).isEqualTo(new Country(
                1,"AF","AFGHANISTAN","Afghanistan","AFG",4,93
        ));

    }
}
