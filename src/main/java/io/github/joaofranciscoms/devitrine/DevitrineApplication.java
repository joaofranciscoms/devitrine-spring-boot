package io.github.joaofranciscoms.devitrine;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class DevitrineApplication {

	public static void main(String[] args) {
		SpringApplication.run(DevitrineApplication.class, args);
	}

}
