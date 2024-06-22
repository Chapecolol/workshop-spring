package com.edivancarvalho.workshopmongo.env;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.github.cdimascio.dotenv.Dotenv;

@Configuration
public class DotEnv {
	@Bean
	public Dotenv dotenv() {
		return Dotenv.load();
	}
}
