package com.desafio03.msusers;

import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
@EnableRabbit
public class Desafio03Application {

	public static void main(String[] args) {
		SpringApplication.run(Desafio03Application.class, args);
	}

}
