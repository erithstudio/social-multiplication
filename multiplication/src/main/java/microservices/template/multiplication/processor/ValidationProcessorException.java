package microservices.template.multiplication.processor;

import java.util.List;

public class ValidationProcessorException extends TxgException {

    private List<DtoValidationResponseStatusDetail> details;

    public ValidationProcessorException() {
    }

    public ValidationProcessorException(IErrorConstant error) {
        super(error.getCode(), error.getMessage(), error.getValidationRule());
    }

    public ValidationProcessorException(IErrorConstant error, String field) {
        super(error.getCode(), error.getMessage(), field, error.getValidationRule());
    }

    public ValidationProcessorException(IErrorConstant error, String... templateArgs) {
        super(error.getCode(), error.getMessage(), error.getValidationRule(), templateArgs);
    }

    public ValidationProcessorException(List<DtoValidationResponseStatusDetail> details) {
        this.details = details;
    }

    public ValidationProcessorException(String message, Throwable cause) {
        super(message, cause);
    }

    public ValidationProcessorException(String message) {
        super(message);
    }

    public ValidationProcessorException(Throwable cause) {
        super(cause);
    }

    public ValidationProcessorException(String errorCode, String messageTemplate, String field, String validationRule) {
        super(errorCode, messageTemplate, field, validationRule);
    }

    public ValidationProcessorException(String errorCode, String messageTemplate, String field, String validationRule, String[] templateArgs) {
        super(errorCode, messageTemplate, field, validationRule, templateArgs);
    }

    public ValidationProcessorException(String errorCode, String messageTemplate, String validationRule, String... templateArgs) {
        super(errorCode, messageTemplate, validationRule, templateArgs);
    }

    public ValidationProcessorException(Throwable cause, String errorCode, String messageTemplate, String validationRule, String... templateArgs) {
        super(cause, errorCode, messageTemplate, validationRule, templateArgs);
    }

    public List<DtoValidationResponseStatusDetail> getDetails() {
        return details;
    }

    public void setDetails(List<DtoValidationResponseStatusDetail> details) {
        this.details = details;
    }
}
