package com.jh.simply;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

// @SpringBootApplication
@SpringBootApplication(exclude = { DataSourceAutoConfiguration.class })
public class SimplyApplication {

	public static void main(String[] args) {
		SpringApplication.run(SimplyApplication.class, args);
	}

}
