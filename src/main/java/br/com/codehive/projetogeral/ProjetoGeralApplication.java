package br.com.codehive.projetogeral;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;

@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
@CrossOrigin
public class ProjetoGeralApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProjetoGeralApplication.class, args);
	}

	@Bean
	public PasswordEncoder getPasswordEncoder(){
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		return encoder;
	}

}
