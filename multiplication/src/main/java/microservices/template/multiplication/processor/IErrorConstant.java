package microservices.template.multiplication.processor;

public interface IErrorConstant {

    String getCode();

    String getMessage();

    String getValidationRule();
}
