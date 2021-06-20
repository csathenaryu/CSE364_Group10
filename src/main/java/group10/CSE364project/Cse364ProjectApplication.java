package group10.CSE364project;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
public class Cse364ProjectApplication  extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(Cse364ProjectApplication.class, args);
	}

}
