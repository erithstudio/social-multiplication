package microservices.template.apigateway.configuration;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        System.out.println(":::: Load WebSecurityConfig");
        http.authorizeRequests()
            .anyRequest().authenticated()
                .and().httpBasic();
        http.cors();
    }
}