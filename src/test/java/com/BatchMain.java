package com;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class BatchMain {
	public static void main(String[] args) {
		ApplicationContext contex = SpringApplication.run(BatchMain.class, args);
	}
}
