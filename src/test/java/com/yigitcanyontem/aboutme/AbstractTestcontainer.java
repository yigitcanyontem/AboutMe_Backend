package com.yigitcanyontem.aboutme;

import com.github.javafaker.Faker;
import org.junit.jupiter.api.BeforeAll;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.util.Locale;

@Testcontainers
public abstract class AbstractTestcontainer {

    //oreAll
    //ic void beforeAll() {

    //Locale.setDefault(Locale.US);
    //Flyway flyway = Flyway.configure().dataSource(
    //        postgreSQLContainer.getJdbcUrl(),
    //        postgreSQLContainer.getUsername(),
    //        postgreSQLContainer.getPassword()
    //).load();
    //flyway.migrate();
    //

    //tainer
    //ected static final PostgreSQLContainer<?> postgreSQLContainer
    //    = new PostgreSQLContainer<>("postgres:latest")
    //    .withDatabaseName("aboutme-dao-unit-test")
    //    .withUsername("postgres")
    //    .withPassword("password")
    //    .withReuse(true);

    //amicPropertySource
    //ate static void registerDataSourceProperties(DynamicPropertyRegistry registry){
    //registry.add(
    //        "spring.datasource.url",
    //        postgreSQLContainer::getJdbcUrl

    //);
    //registry.add(
    //        "spring.datasource.username",
    //        postgreSQLContainer::getUsername

    //);
    //registry.add(
    //        "spring.datasource.password",
    //        postgreSQLContainer::getPassword

    //);
    //}
    protected static final Faker FAKER = new Faker();

}
