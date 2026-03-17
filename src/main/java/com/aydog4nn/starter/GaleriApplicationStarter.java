package com.aydog4nn.starter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.persistence.autoconfigure.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


@ComponentScan(basePackages = {"com.aydog4nn"})
@EntityScan(basePackages = {"com.aydog4nn"})
@EnableJpaRepositories(basePackages = {"com.aydog4nn"})
@SpringBootApplication
public class GaleriApplicationStarter {

	public static void main(String[] args) {
		SpringApplication.run(GaleriApplicationStarter.class, args);
	}

}
