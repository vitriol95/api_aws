package vitriol.api_aws;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

//@EnableJpaAuditing
@SpringBootApplication
public class ApiAwsApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApiAwsApplication.class, args);
	}

}
