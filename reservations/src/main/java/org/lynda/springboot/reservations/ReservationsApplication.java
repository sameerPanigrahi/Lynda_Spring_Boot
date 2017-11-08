package org.lynda.springboot.reservations;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EntityScan("org.lynda.data.entity")
@EnableAutoConfiguration
//@EnableJpaRepositories("org.lynda.repository")
@ComponentScan(basePackages = {"org.lynda.data.entity", "org.lynda.service", "org.lynda.controllers", "org.lynda.repository"})
public class ReservationsApplication {

	public static void main(String[] args) {
		SpringApplication.run(ReservationsApplication.class, args);
	}
}
