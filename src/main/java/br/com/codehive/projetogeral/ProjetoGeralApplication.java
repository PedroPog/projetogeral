package br.com.codehive.projetogeral;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class ProjetoGeralApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProjetoGeralApplication.class, args);
	}

	@GetMapping("/")
	public String hello() {
		return String.format("Hello world!");
	}
}
