package microservices.template;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.thepavel.icomponent.InterfaceComponentScan;

@EnableDiscoveryClient
@EnableEurekaClient
@SpringBootApplication
@EnableJpaRepositories("microservices.template.multiplication.repository")
@InterfaceComponentScan
public class MultiplicationApplication {

	public static void main(String[] args) {
		SpringApplication.run(MultiplicationApplication.class, args);
	}

}
