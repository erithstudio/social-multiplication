package microservices.template.multiplication.configuration;

import microservices.template.multiplication.helper.annotation.ProcessorComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ProcessorComponentScan(basePackages = "microservices.template.multiplication.processor.executor")
public class ExecutorConfiguration {
}
