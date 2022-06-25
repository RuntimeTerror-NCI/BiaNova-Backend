package com.bianova.control;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;

import java.io.File;
import java.io.IOException;

@SpringBootApplication
public class ControlApplication {

	public static void main(String[] args) throws IOException {
		SpringApplication.run(ControlApplication.class, args);
	}

}
