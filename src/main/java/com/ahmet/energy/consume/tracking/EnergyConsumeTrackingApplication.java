package com.ahmet.energy.consume.tracking;


import com.ahmet.energy.consume.tracking.entity.Energy;
import com.ahmet.energy.consume.tracking.service.EnergyService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@SpringBootApplication
public class EnergyConsumeTrackingApplication {

	public static void main(String[] args) {
		SpringApplication.run(EnergyConsumeTrackingApplication.class, args);
	}


	@Bean
	CommandLineRunner runner(EnergyService energyService) {
		return args -> {
			// read json and write to in-memory as pojo

			ObjectMapper mapper = new ObjectMapper();
			InputStream inputStream = new FileInputStream(new File("src/main/resources/json/powerProduced.json"));
			TypeReference<List<Energy>> typeReference = new TypeReference<List<Energy>>() {};

			try {
				List<Energy> energyList = mapper.readValue(inputStream, typeReference);
				energyService.save(energyList);
				System.out.println("Energy document Saved!");
			} catch (IOException e){
				System.out.println("Unable to save energy document: " + e.getMessage());
			}
		};
	}
}
