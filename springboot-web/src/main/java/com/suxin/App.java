package com.suxin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication(scanBasePackages={"com.suxin.controller","com.suxin.aspect"})
@EnableAsync
public class App {
	public static void main(String[] args) {
		SpringApplication.run(App.class, args);
	}

}
