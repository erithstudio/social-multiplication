package microservices.template.multiplication.autoconfig;

import com.google.common.collect.Sets;
import lombok.extern.slf4j.Slf4j;
import microservices.template.multiplication.helper.annotation.GameMapper;
import microservices.template.multiplication.helper.annotation.GameValidator;
import microservices.template.multiplication.helper.annotation.ProcessorComponentScan;
import microservices.template.multiplication.processor.IExecutorProcessor;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ResourceLoader;

import java.util.Arrays;
import java.util.Set;

@Slf4j
public class ExecutorAutoConfigureRegistrar extends BaseAutoConfigureRegistrar {

    @Override
    public Class getAnnotationClass() {
        return ProcessorComponentScan.class;
    }

    @Override
    public void registerBeanDefinitions(BeanDefinitionRegistry registry, Environment environment, ResourceLoader resourceLoader, Set<String> packagesToScan) {
        MetaBeanDefinitionScanner scanner =
                new MetaBeanDefinitionScanner(registry, environment, resourceLoader, getFilterClasses(packagesToScan));
        scanner.scan(packagesToScan.toArray(new String[]{}));
    }

    private Class[] getFilterClasses(Set<String> packagesToScan) {
        Class baseClass = IExecutorProcessor.class;
        Set<Class> classToScan = Sets.newHashSet(baseClass);
        try {
            for (String packageName : packagesToScan) {
                Class[] params = getClasses(packageName);
                for (int i = 0; i < params.length; i++) {
                    if (Arrays.asList(params[i].getSuperclass().getInterfaces()).contains(baseClass)) {
                        GameMapper mapper = (GameMapper) params[i].getAnnotation(GameMapper.class);
                        for (Class mapperClass : mapper.value()) {
                            packagesToScan.add(mapperClass.getPackage().getName());
                            classToScan.add(mapperClass);
                        }

                        GameValidator validator = (GameValidator) params[i].getAnnotation(GameValidator.class);
                        for (Class mapperClass : validator.value()) {
                            packagesToScan.add(mapperClass.getPackage().getName());
                            classToScan.add(mapperClass);
                        }
                    }
                }
            }
        } catch (Exception e) {
            log.error("error getFilterClasses", e);
        }
        return classToScan.toArray(new Class[0]);
    }
}
