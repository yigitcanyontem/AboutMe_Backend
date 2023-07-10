package com.yigitcanyontem.aboutme;

import com.yigitcanyontem.aboutme.security.auth.AuthenticationService;
import com.yigitcanyontem.aboutme.security.auth.RegisterRequest;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.sql.Date;
import java.util.Locale;

import static com.yigitcanyontem.aboutme.users.Role.*;

@SpringBootApplication
public class AboutmeApplication {

	public static void main(String[] args) {
		Locale.setDefault(Locale.US);
		SpringApplication.run(AboutmeApplication.class, args);
	}


}
