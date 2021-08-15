package microservices.template.multiplication.processor.validator;

import microservices.template.multiplication.domain.DtoAccountBalanceRequest;
import microservices.template.multiplication.processor.ABaseValidatorProcessor;
import microservices.template.multiplication.processor.ValidationError;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

import static microservices.template.multiplication.processor.ValidationErrorsFactory.noErrors;
import static microservices.template.multiplication.processor.ValidationErrorsFactory.oneNotFoundError;

public class AccountCurrencyExistValidator extends ABaseValidatorProcessor<DtoAccountBalanceRequest> {

    @Override
    public List<ValidationError> process(HttpServletRequest servletRequest, DtoAccountBalanceRequest internal) {
        if (internal.getCurrency() == null) {
            return oneNotFoundError("currency");
        }
        return noErrors();
    }

}
