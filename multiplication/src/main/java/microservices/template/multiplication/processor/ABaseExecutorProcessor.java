package microservices.template.multiplication.processor;

import lombok.Getter;
import ma.glasnost.orika.MapperFacade;

@Getter
public abstract class ABaseExecutorProcessor<R, T> implements IExecutorProcessor<R, T> {

    private final MapperFacade mapperFacade;

    protected ABaseExecutorProcessor(MapperFacade mapperFacade) {
        this.mapperFacade = mapperFacade;
    }
}
