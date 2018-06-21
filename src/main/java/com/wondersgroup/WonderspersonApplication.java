package com.wondersgroup;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class WonderspersonApplication {

	public static void main(String[] args) {
		SpringApplication.run(WonderspersonApplication.class, args);
	}
}
