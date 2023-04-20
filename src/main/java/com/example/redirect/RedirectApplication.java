package com.example.redirect;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
public class RedirectApplication {

	public static void main(String[] args) {
		SpringApplication.run(RedirectApplication.class, args);
	}
	@Bean
	public String string(){
		return new String();
	}

}
