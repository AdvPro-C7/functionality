package id.ac.ui.cs.advprog.functionality;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
@EnableAsync
@Configuration
public class FunctionalityApplication {

	public static void main(String[] args) {
		SpringApplication.run(FunctionalityApplication.class, args);
	}

}
