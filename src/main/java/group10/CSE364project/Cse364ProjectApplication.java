package group10.CSE364project;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
public class Cse364ProjectApplication {

	public static void main(String[] args) {
		SpringApplication.run(Cse364ProjectApplication.class, args);
	}

}
