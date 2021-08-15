package microservices.template.multiplication.configuration;

import microservices.template.multiplication.processor.IModuleProperties;
import microservices.template.multiplication.processor.IServiceProperties;
import microservices.template.multiplication.processor.ModuleProperties;
import microservices.template.multiplication.processor.ServiceProperties;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
public class MultiplicationModuleConfig {
    public static final String SERVICE_NUMBER = "7";
    public static final String MODULE_NUMBER = "2";

    @Bean
    @ConditionalOnMissingBean
    public IServiceProperties serviceProperties() {
        return new ServiceProperties(SERVICE_NUMBER);
    }

    @Bean
    @Primary
    public IModuleProperties moduleProperties() {
        return new ModuleProperties(serviceProperties(), MODULE_NUMBER);
    }
}
