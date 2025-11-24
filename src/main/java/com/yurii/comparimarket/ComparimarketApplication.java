package com.yurii.comparimarket;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ComparimarketApplication {

	public static void main(String[] args) {
		System.setProperty("server.port", "9091");
		SpringApplication.run(ComparimarketApplication.class, args);
	}

}
