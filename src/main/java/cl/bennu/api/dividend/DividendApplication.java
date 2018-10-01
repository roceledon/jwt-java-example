package cl.bennu.api.dividend;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableAutoConfiguration
@SpringBootApplication
public class DividendApplication {
	final static Logger logger = LoggerFactory.getLogger(DividendApplication.class);
	public static void main(String[] args) {
		SpringApplication.run(DividendApplication.class, args);
	}
}
