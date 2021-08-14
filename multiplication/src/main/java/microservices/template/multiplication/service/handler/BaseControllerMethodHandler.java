package microservices.template.multiplication.service.handler;

import lombok.Data;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.thepavel.icomponent.handler.MethodHandler;

@Data
public abstract class BaseControllerMethodHandler implements MethodHandler, ApplicationContextAware {
    private ApplicationContext applicationContext;
}
