package com.example.demo.main;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.stereotype.Repository;

@SpringBootApplication
@ComponentScan(basePackages = {"com.example.demo"})
@EntityScan("com.example.demo.model")
@EnableMongoRepositories("com.example.demo.repositories")
public class SpringMongoDbCurdApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringMongoDbCurdApplication.class, args);
	}

}
