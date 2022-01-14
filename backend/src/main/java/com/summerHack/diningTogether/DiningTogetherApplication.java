package com.summerHack.diningTogether;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition(info = @Info(title = "Dining Together API", version = "1.0"))
public class DiningTogetherApplication {

	public static void main(String[] args) {
		SpringApplication.run(DiningTogetherApplication.class, args);
	}

}
