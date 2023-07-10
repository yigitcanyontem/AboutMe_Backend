package com.yigitcanyontem.aboutme.users;

import com.yigitcanyontem.aboutme.AbstractTestcontainer;
import com.yigitcanyontem.aboutme.country.Country;
import com.yigitcanyontem.aboutme.country.CountryRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.ApplicationContext;

import java.sql.Date;
import java.util.Locale;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class UsersRepositoryTest extends AbstractTestcontainer {
    @Autowired
    private UsersRepository underTest;
    @Autowired
    private ApplicationContext applicationContext;
    @Autowired
    private CountryRepository countryRepository;
    @BeforeEach
    void setUp() {
        Locale.setDefault(Locale.US);
    }
    //@Test
    //void getUsersByUsername() {
    //  String username = "username";
    //  Users users = new Users(
    //          FAKER.name().firstName(),
    //          FAKER.name().lastName(),
    //          Date.valueOf("2015-03-30"),
    //          countryRepository.findById(1).get(),
    //          FAKER.internet().safeEmailAddress(),
    //          "username",
    //          "password");
    //  underTest.save(users);
//
    //  // When
    //  Users actual = underTest.getUsersByUsername(username);
    //  // Then
    //  assertThat(actual).isEqualTo(users);
//
    //}
}//