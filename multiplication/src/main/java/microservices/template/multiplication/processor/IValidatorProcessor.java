package microservices.template.multiplication.processor;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface IValidatorProcessor<T> extends IBaseProcessor<List<ValidationError>, T> {

    @Override
    List<ValidationError> process(HttpServletRequest servletRequest, T request);

}
