package microservices.template.multiplication.processor;

import java.util.Arrays;
import java.util.Objects;

public final class ValidationError {

    private final IErrorConstant errorConstant;
    private final Object[] messageArguments;
    private String fieldName;

    private ValidationError(IErrorConstant errorConstant, Object[] messageArguments) {
        this.errorConstant = errorConstant;
        this.messageArguments = messageArguments;
    }

    public static ValidationError of(IErrorConstant errorConstant, Object... messageArguments) {
        return new ValidationError(errorConstant, messageArguments);
    }

    public ValidationError withFieldName(String fieldName) {
        this.fieldName = fieldName;
        return this;
    }

    public IErrorConstant getErrorConstant() {
        return errorConstant;
    }

    public Object[] getMessageArguments() {
        return messageArguments;
    }

    public String getFieldName() {
        return fieldName;
    }

    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        ValidationError that = (ValidationError) o;
        return Objects.equals(errorConstant, that.errorConstant)
                && Arrays.equals(messageArguments, that.messageArguments)
                && Objects.equals(fieldName, that.fieldName);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(errorConstant, fieldName);
        result = 31 * result + Arrays.hashCode(messageArguments);
        return result;
    }

    @Override
    public String toString() {
        return "ValidationError{"
                + " errorConstant=" + errorConstant
                + ", messageArguments=" + Arrays.toString(messageArguments)
                + ", fieldName='" + fieldName + '\''
                + '}';
    }
}
