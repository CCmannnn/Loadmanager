package com.zeng;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author Administrator
 */
@SpringBootApplication
public class DemoApplication {

	@RequestMapping("/")
	public String greeting() {

		return "Hello World!";
	}

	public static void main(String[] args) {

		SpringApplication.run(DemoApplication.class, args);
	}
}
