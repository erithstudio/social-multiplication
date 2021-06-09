package microservices.book.multiplication;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories("microservices.book.gamification.repository")
public class SocialMultiplicationApplication {

	public static void main(String[] args) {
		SpringApplication.run(SocialMultiplicationApplication.class, args);
	}

}
