package id.ac.ui.cs.advprog.functionality;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
public class FunctionalityApplication {

	public static void main(String[] args) {
		SpringApplication.run(FunctionalityApplication.class, args);
	}

}
