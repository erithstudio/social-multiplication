package microservices.template.apigateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@EnableZuulProxy
//@RestController
@SpringBootApplication
//@ComponentScan("microservices.template.apigateway.*")
public class APIGatewayApplication {

//    @Bean
//    public WebMvcConfigurer CORSConfigurer() {
//        return new WebMvcConfigurer() {
//            @Override
//            public void addCorsMappings(CorsRegistry registry) {
//                System.out.println("::::::::: addCorsMappings A");
//                registry.addMapping("/**")
//                        .allowedOrigins("*")
//                        .allowedHeaders("*")
//                        .allowedMethods("GET", "POST", "PUT", "DELETE", "HEAD", "OPTIONS", "PATCH")
//                        .maxAge(-1)   // add maxAge
//                        .allowCredentials(false);
//            }
//        };
//    }

    public static void main(String[] args) {
        SpringApplication.run(APIGatewayApplication.class, args);
    }

}
