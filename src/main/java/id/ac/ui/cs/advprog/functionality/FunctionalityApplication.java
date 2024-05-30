package id.ac.ui.cs.advprog.functionality;

import id.ac.ui.cs.advprog.functionality.config.CorsConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
@Import(CorsConfig.class)
@EnableAsync
@Configuration
@ComponentScan("id.ac.ui.cs.advprog.functionality")
public class FunctionalityApplication {

	public static void main(String[] args) {
		SpringApplication.run(FunctionalityApplication.class, args);
	}

}
