package microservices.template.multiplication.processor;

import java.util.List;

public interface IExecutorProcessor<R, T> extends IBaseProcessor<R, T> {
    List<IValidatorProcessor> getValidators();
}
