package microservices.template.multiplication.processor;

import microservices.template.multiplication.enumeration.ErrorConstantsEnum;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Function;

@SuppressWarnings({"WeakerAccess", "unused"})
public class ValidationErrorsFactory {

    //=========== Single error factory methods =====================

    public static ValidationError fieldError(IErrorConstant errorConstant, String fieldName) {
        return ValidationError.of(errorConstant)
                .withFieldName(fieldName);
    }

    public static ValidationError fieldErrorWithMessageArguments(IErrorConstant errorConstant, String fieldName, Object... messageArguments) {
        return ValidationError.of(errorConstant, messageArguments)
                .withFieldName(fieldName);
    }

    public static ValidationError error(IErrorConstant errorConstant) {
        return ValidationError.of(errorConstant);
    }

    public static ValidationError errorWithMessageArguments(IErrorConstant errorConstant, Object... messageArguments) {
        return ValidationError.of(errorConstant, messageArguments);
    }

    public static ValidationError notFound(String fieldName) {
        return ValidationError.of(ErrorConstantsEnum.NOT_FOUND)
                .withFieldName(fieldName);
    }

    public static ValidationError notAllowed(String fieldName) {
        return ValidationError.of(ErrorConstantsEnum.NOT_ALLOWED)
                .withFieldName(fieldName);
    }

    public static ValidationError notUnique(String fieldName) {
        return ValidationError.of(ErrorConstantsEnum.NOT_UNIQUE)
                .withFieldName(fieldName);
    }

    public static ValidationError invalidValue(String fieldName) {
        return ValidationError.of(ErrorConstantsEnum.INVALID_VALUE)
                .withFieldName(fieldName);
    }

    public static ValidationError minValueViolation(String fieldName) {
        return ValidationError.of(ErrorConstantsEnum.MIN_VALUE_VIOLATION)
                .withFieldName(fieldName);
    }

    public static ValidationError maxValueViolation(String fieldName) {
        return ValidationError.of(ErrorConstantsEnum.MAX_VALUE_VIOLATION)
                .withFieldName(fieldName);
    }

    public static ValidationError noAccess(String fieldName) {
        return ValidationError.of(ErrorConstantsEnum.NO_ACCESS)
                .withFieldName(fieldName);
    }

    //=========== List of errors factory methods =====================

    public static List<ValidationError> noErrors() {
        return Arrays.asList();
    }

    public static List<ValidationError> oneFieldError(IErrorConstant errorConstant, String fieldName) {
        return Arrays.asList(fieldError(errorConstant, fieldName));
    }

    public static List<ValidationError> oneFieldErrorWithMessageArguments(IErrorConstant errorConstant, String fieldName, Object... messageArguments) {
        return Arrays.asList(fieldErrorWithMessageArguments(errorConstant, fieldName, messageArguments));
    }

    public static List<ValidationError> oneError(IErrorConstant errorConstant) {
        return Arrays.asList(error(errorConstant));
    }

    public static List<ValidationError> oneErrorWithMessageArguments(IErrorConstant errorConstant, Object... messageArguments) {
        return Arrays.asList(errorWithMessageArguments(errorConstant, messageArguments));
    }

    public static List<ValidationError> oneNotFoundError(String fieldName) {
        return Arrays.asList(notFound(fieldName));
    }

    public static List<ValidationError> oneNotAllowedError(String fieldName) {
        return Arrays.asList(notAllowed(fieldName));
    }

    public static List<ValidationError> oneNotUniqueError(String fieldName) {
        return Arrays.asList(notUnique(fieldName));
    }

    public static List<ValidationError> oneInvalidValueError(String fieldName) {
        return Arrays.asList(invalidValue(fieldName));
    }

    public static List<ValidationError> oneMinValueViolationError(String fieldName) {
        return Arrays.asList(minValueViolation(fieldName));
    }

    public static List<ValidationError> oneMaxValueViolationError(String fieldName) {
        return Arrays.asList(maxValueViolation(fieldName));
    }

    public static List<ValidationError> oneNoAccessError(String fieldName) {
        return Arrays.asList(noAccess(fieldName));
    }

    /**
     * Iterates over collection of items and collects validation errors provided by validatonErrorsProvider.
     *
     * @param collection              collection of items to validate
     * @param validatonErrorsProvider function that takes current item together with its index and generates a collection
     *                                of validation errors or empty collection if there are none
     * @param <T>                     item type
     * @return all validation errors generated from all items
     */
    public static <T> List<ValidationError> validateCollection(
            Collection<T> collection,
            BiFunction<T, Integer, Collection<ValidationError>> validatonErrorsProvider
    ) {
        int index = 0;
        List<ValidationError> errors = new ArrayList<>();
        for (T element : collection) {
            errors.addAll(validatonErrorsProvider.apply(element, index++));
        }
        return errors;
    }

    /**
     * Iterates over collection of items and collects validation errors provided by validatonErrorsProvider.
     *
     * @param collection      collection of items to validate
     * @param fieldPathFormat format string for java.lang.String.format() method using %s as placeholder for item index,
     *                        e.g. limits[%s].amount
     * @param errorConstant   error constant to use for generated validation errors
     * @param validator       function that takes item and return true if item is valid, otherwise returns false
     * @param <T>             item type
     * @return all validation errors generated from all items
     */
    public static <T> List<ValidationError> validateCollection(
            Collection<T> collection,
            String fieldPathFormat,
            IErrorConstant errorConstant,
            Function<T, Boolean> validator
    ) {
        int index = 0;
        List<ValidationError> errors = new ArrayList<>();
        for (T element : collection) {
            if (!validator.apply(element)) {
                errors.add(ValidationError.of(errorConstant)
                        .withFieldName(String.format(fieldPathFormat, index)));
            }
            index++;
        }
        return errors;
    }
}
