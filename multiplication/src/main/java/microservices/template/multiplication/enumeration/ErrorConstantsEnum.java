package microservices.template.multiplication.enumeration;

import microservices.template.multiplication.processor.IErrorConstant;

public enum ErrorConstantsEnum implements IErrorConstant {

    // keep code 1 for compatibility with a lot of tests checking validation errors
    VALIDATION_ERROR("1", "Validation error", "validation_error"),
    INTERNAL_ERROR("2", "Internal error", "internal_server_error"),
    FEIGN_CLIENT_ERROR("30", "FeignClient externalCode: %s externalDescription: %s", "feign_client_error"),
    NOT_FOUND("40", "%s not found", "not_found"),
    NOT_ALLOWED("50", "%s not allowed", "not_allowed"),
    NOT_UNIQUE("60", "%s not unique", "not_unique"),
    INVALID_VALUE("70", "%s invalid value", "invalid_value"),
    MIN_VALUE_VIOLATION("80", "%s min value violated", "min_value_violation"),
    MAX_VALUE_VIOLATION("90", "%s max value violated", "max_value_violation"),
    NO_ACCESS("100", "%s access denied", "access_denied"),
    NOT_EQUAL("110", "%s not equal", "not_equal"),
    IDENTICAL_VIOLATION("120", "%s identical", "identical"),
    START_TIME_COT_VIOLATION("130", "%s is before start time", "start_cut_off_time"),
    END_TIME_COT_VIOLATION("140", "%s is after end time", "end_cut_off_time");

    private String code;
    private String message;
    private String validationRule;

    ErrorConstantsEnum(String code, String message, String validationRule) {
        this.code = code;
        this.message = message;
        this.validationRule = validationRule;

    }

    @Override
    public String getCode() {
        return code;
    }

    @Override
    public String getMessage() {
        return message;
    }

    @Override
    public String getValidationRule() {
        return validationRule;
    }

    public String toString() {
        return code;
    }
}
