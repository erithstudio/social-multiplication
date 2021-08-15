package microservices.template.multiplication.processor;

public interface IModuleProperties {
    String getServiceNumber();

    String getModuleNumber();

    default String baseCode() {
        return getServiceNumber() + "." + getModuleNumber();
    }

    default String errorCode(IErrorConstant errorConstant) {
        return baseCode() + "." + errorConstant.getCode();
    }
}
