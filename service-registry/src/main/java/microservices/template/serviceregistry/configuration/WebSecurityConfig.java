package microservices.template.serviceregistry.configuration;

import de.codecentric.boot.admin.server.config.AdminServerProperties;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import java.util.UUID;

@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    private final String adminContextPath;

    public WebSecurityConfig(AdminServerProperties adminServerProperties) {
        this.adminContextPath = adminServerProperties.getContextPath();
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        SavedRequestAwareAuthenticationSuccessHandler successHandler = new SavedRequestAwareAuthenticationSuccessHandler();
        successHandler.setTargetUrlParameter("redirectTo");
        successHandler.setDefaultTargetUrl(adminContextPath + "/");

        http.headers().frameOptions().disable().and()
                .authorizeRequests()
                .antMatchers(adminContextPath + "/").permitAll()
                .antMatchers("/resources/**").permitAll()
                .antMatchers("/**.css").permitAll()
                .antMatchers("*.css").permitAll()
                .antMatchers(adminContextPath + "/assets/**").permitAll()
                .antMatchers(adminContextPath + "/login").permitAll()
                .antMatchers(adminContextPath + "/eureka/**").permitAll()
                .antMatchers(adminContextPath + "/eureka-dashboard").permitAll()
                .antMatchers(adminContextPath + "/eureka/**/**").permitAll()
                .antMatchers(adminContextPath + "/eureka/**/**/**").permitAll()
                .antMatchers(adminContextPath + "/instances/**").permitAll()
                .antMatchers(adminContextPath + "/instances").permitAll()
                .antMatchers(adminContextPath + "/actuator/**").permitAll()
                .antMatchers("/login.html", "/**/**/*.css", "/**/**/**/*.css", "/**/*.css", "/img/**", "/third-party/**").permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin().loginPage(adminContextPath + "/login").successHandler(successHandler).and()
                .logout().logoutUrl(adminContextPath + "/logout").and()
                .httpBasic().and()
                .csrf().ignoringAntMatchers(adminContextPath + "/", adminContextPath + "/login", adminContextPath + "/eureka/**", adminContextPath + "/eureka/**/**", adminContextPath + "/eureka/**/**/**", adminContextPath + "/eureka-dashboard", adminContextPath + "/instances/**", adminContextPath + "/**.css")
                .csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())
                .ignoringRequestMatchers(
                        new AntPathRequestMatcher(adminContextPath + "/instances", HttpMethod.POST.toString()),
                        new AntPathRequestMatcher(adminContextPath + "/instances/*", HttpMethod.DELETE.toString()),
                        new AntPathRequestMatcher(adminContextPath + "/actuator/**")
                ).and()
                .rememberMe()
                .key(UUID.randomUUID().toString())
                .tokenValiditySeconds(1209600);
    }
}
