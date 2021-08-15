package microservices.template.multiplication.service.handler.method.impl;

import microservices.template.multiplication.service.handler.method.ABaseMethodHandler;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;

import java.lang.reflect.Method;

public class SearchMethodHandler extends ABaseMethodHandler {
    public SearchMethodHandler(ApplicationContext context) {
        super(context);
    }

    @Override
    public Object handle(String objectId, Object[] arguments) throws Exception {
        ConfigurableListableBeanFactory beanFactory = ((ConfigurableApplicationContext) getApplicationContext()).getBeanFactory();
        Object executorObject = beanFactory.getBean(objectId);

        Class[] classTypes = getCastClassesFromObject(arguments);
        Method processMethod = findMethod(executorObject, "process", classTypes);
        return processMethod.invoke(executorObject, arguments);
    }
}
