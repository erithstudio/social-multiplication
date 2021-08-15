package microservices.template.multiplication.processor;

import lombok.Getter;
import lombok.Setter;
import ma.glasnost.orika.MapperFacade;
import microservices.template.multiplication.helper.annotation.GameValidator;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import java.util.ArrayList;
import java.util.List;

@Getter
public abstract class ABaseExecutorProcessor<R, T> implements IExecutorProcessor<R, T>, ApplicationContextAware {
    @Setter
    private ApplicationContext applicationContext;

    private List<IValidatorProcessor> validators = new ArrayList<>();

    private final MapperFacade mapperFacade;
    private final IModuleProperties moduleProperties;

    protected ABaseExecutorProcessor(MapperFacade mapperFacade, IModuleProperties moduleProperties) {
        this.mapperFacade = mapperFacade;
        this.moduleProperties = moduleProperties;
    }

    public List<IValidatorProcessor> getValidators() {
        if (validators.isEmpty()) {
            for (Class mapperClass : getClass().getAnnotation(GameValidator.class).value()) {
                validators.add((IValidatorProcessor) applicationContext.getBean(mapperClass));
            }
        }
        return validators;
    }
}
