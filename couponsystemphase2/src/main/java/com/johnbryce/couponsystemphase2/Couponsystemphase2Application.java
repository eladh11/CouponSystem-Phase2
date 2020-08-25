package com.johnbryce.couponsystemphase2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.johnbryce.couponsystemphase2.exception.AlreadyExitException;
import com.johnbryce.couponsystemphase2.exception.IncorrectDetailsException;

@SpringBootApplication
public class Couponsystemphase2Application {

	public static void main(String[] args) throws AlreadyExitException, IncorrectDetailsException {
		SpringApplication.run(Couponsystemphase2Application.class, args);
		System.out.println("END...");
	}

}
