package com.johnbryce.couponsystemphase2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

import com.johnbryce.couponsystemphase2.exception.AlreadyExitException;
import com.johnbryce.couponsystemphase2.exception.IncorrectDetailsException;

@SpringBootApplication
@EnableScheduling
public class Couponsystemphase2Application {

	public static void main(String[] args) throws AlreadyExitException, IncorrectDetailsException {
		SpringApplication.run(Couponsystemphase2Application.class, args);
		System.out.println("END...");
	}

}
