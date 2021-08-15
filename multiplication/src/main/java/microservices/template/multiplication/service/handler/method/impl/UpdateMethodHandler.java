package microservices.template.multiplication.service.handler.method.impl;

import microservices.template.multiplication.service.handler.method.ABaseMethodHandler;

import java.lang.reflect.Method;

public class UpdateMethodHandler extends ABaseMethodHandler {
    @Override
    public Object handle(Object executorObject, Object[] arguments) throws Exception {
        Class[] classTypes = getCastClassesFromObject(arguments);
        Method processMethod = findMethod(executorObject, "process", classTypes);
        return processMethod.invoke(executorObject, arguments);
    }
}
