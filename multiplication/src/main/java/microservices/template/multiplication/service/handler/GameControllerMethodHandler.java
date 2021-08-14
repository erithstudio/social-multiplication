package microservices.template.multiplication.service.handler;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import microservices.template.multiplication.enumeration.CallTypeEnum;
import microservices.template.multiplication.helper.CallHandlerUtils;
import microservices.template.multiplication.service.handler.method.MethodHandlerFactory;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Component;
import org.thepavel.icomponent.metadata.MethodMetadata;

import java.beans.Introspector;

@Slf4j
@Component
public class GameControllerMethodHandler extends BaseControllerMethodHandler {

    @SneakyThrows
    @Override
    public Object handle(Object[] arguments, MethodMetadata methodMetadata) {
        String callType = CallHandlerUtils.getTypeName(methodMetadata.getSourceMethod().getName());
        String executorId = Introspector.decapitalize(CallHandlerUtils.getExecutorId(methodMetadata.getSourceMethod().getName()));
        ConfigurableListableBeanFactory beanFactory = ((ConfigurableApplicationContext) getApplicationContext()).getBeanFactory();
        return MethodHandlerFactory.getInstance().execute(CallTypeEnum.valueOf(callType.toUpperCase()), beanFactory.getBean(executorId), arguments);
    }


}