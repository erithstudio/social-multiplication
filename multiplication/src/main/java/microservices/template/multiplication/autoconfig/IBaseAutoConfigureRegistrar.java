package microservices.template.multiplication.autoconfig;

import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ResourceLoader;

import java.util.Set;

public interface IBaseAutoConfigureRegistrar {
    Class getAnnotationClass();

    void registerBeanDefinitions(BeanDefinitionRegistry registry, Environment environment, ResourceLoader resourceLoader, Set<String> packagesToScan);
}
