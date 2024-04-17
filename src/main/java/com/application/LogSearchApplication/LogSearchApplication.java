package com.application.LogSearchApplication;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {
		"com.application"
})
public class LogSearchApplication {

	public static void main(String[] args) {
		SpringApplication.run(LogSearchApplication.class, args);
	}

}
