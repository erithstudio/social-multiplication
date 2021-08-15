package microservices.template.multiplication.processor;

import microservices.template.multiplication.dto.DtoValidationResponseStatusDetail;
import org.apache.commons.lang3.ArrayUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.CollectionUtils;

import javax.servlet.http.HttpServletRequest;
import java.lang.invoke.MethodHandles;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Utility class to perform validations on objects of type T or its supertypes
 * using a list of {@link IValidatorProcessor} instances.
 * <p>
 * The list of collected validation errors is converted to DTO objects and can
 * either be returned directly or thrown in a {@link ValidationProcessorException}
 * from the {@link #validate(Object)} method.
 *
 * @param <T> lower type bound for supported objects to validate using the given
 *            list of validators
 */
public class ValidationDetailsReport<T> {

    // static members and methods

    private static final Logger LOGGER = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

    public static String getDescription(ValidationError error) {
        Object[] messageArguments;
        if (ArrayUtils.isEmpty(error.getMessageArguments())) {
            messageArguments = ArrayUtils.toArray(error.getFieldName());
        } else {
            messageArguments = error.getMessageArguments();
        }
        return String.format(error.getErrorConstant().getMessage(), messageArguments);
    }

    public static <T> ValidationDetailsReport<T> buildUsingSupertypes(List<IValidatorProcessor<? super T>> validators) {
        ValidationDetailsReport<T> report = new ValidationDetailsReport<>(Collections.emptyList());
        report.setValidators(validators);
        return report;
    }

    private IModuleProperties moduleProperties;
    private List<IValidatorProcessor<? super T>> validators;
    private List<Predicate<DtoValidationResponseStatusDetail>> errorPriorities;
    private boolean shouldThrow;

    public ValidationDetailsReport(List<IValidatorProcessor<T>> validators) {
        //noinspection unchecked,rawtypes
        this.validators = (List) validators;
        this.errorPriorities = Collections.emptyList();
        this.shouldThrow = true;
    }

    public ValidationDetailsReport(IValidatorProcessor<T> validator) {
        this.validators = Collections.singletonList(validator);
        this.errorPriorities = Collections.emptyList();
        this.shouldThrow = true;
    }

    // mandatory configuration flags

    public ValidationDetailsReport<T> with(IModuleProperties moduleProperties) {
        this.moduleProperties = moduleProperties;
        return this;
    }

    public ValidationDetailsReport<T> withErrorPriorities(List<Predicate<DtoValidationResponseStatusDetail>> errorPriorities) {
        this.errorPriorities = errorPriorities;
        return this;
    }

    public ValidationDetailsReport<T> shouldReturn() {
        this.shouldThrow = false;
        return this;
    }

    // main functionality

    public List<DtoValidationResponseStatusDetail> validate(HttpServletRequest servletRequest, T internal) throws ValidationProcessorException {
        List<DtoValidationResponseStatusDetail> validationDetails = validators.stream()
                .flatMap(processor -> process(processor, servletRequest, internal))
                .collect(Collectors.toList());
        for (Predicate<DtoValidationResponseStatusDetail> predicate : errorPriorities) {
            Optional<DtoValidationResponseStatusDetail> error = findError(validationDetails, predicate);
            if (error.isPresent()) {
                validationDetails = Arrays.asList(error.get());
                break;
            }
        }
        if (shouldThrow && !CollectionUtils.isEmpty(validationDetails)) {
            ValidationProcessorException exception = new ValidationProcessorException(
                    createValidationErrorMessage(validationDetails));
            exception.setDetails(validationDetails);
            throw exception;
        }
        return validationDetails;
    }

    // misc. (helper methods)

    private void setValidators(List<IValidatorProcessor<? super T>> validators) {
        this.validators = validators;
    }

    private Stream<DtoValidationResponseStatusDetail> process(IValidatorProcessor<? super T> validator, HttpServletRequest servletRequest, T internal) {
        List<ValidationError> errors = validator.process(servletRequest, internal);
        if (errors == null) {
            return Stream.empty();
        }
        return errors.stream()
                .map(error -> createDetail(validator, internal, error));
    }

    private DtoValidationResponseStatusDetail createDetail(
            IValidatorProcessor<? super T> validator, T internal, ValidationError error) {
        IErrorConstant errorConstant = error.getErrorConstant();
        String errorCode = getErrorCode(errorConstant);
        String description = getDescription(error);

        LOGGER.debug("Validator {} returned validation error for object {},\nerrorCode {}, description: {}, field: {}, validation rule: {}",
                validator, internal, errorCode, description, error.getFieldName(), errorConstant.getValidationRule());

        return new DtoValidationResponseStatusDetail(errorCode, description,
                error.getFieldName(), errorConstant.getValidationRule());
    }

    private String getErrorCode(IErrorConstant errorConstant) {
        if (moduleProperties != null) {
            return moduleProperties.errorCode(errorConstant);
        }
        return errorConstant.getCode();
    }

    private Optional<DtoValidationResponseStatusDetail> findError(
            List<DtoValidationResponseStatusDetail> collectedErrors,
            Predicate<DtoValidationResponseStatusDetail> detailPredicate) {
        return collectedErrors.stream().filter(detailPredicate).findFirst();
    }

    private String createValidationErrorMessage(List<DtoValidationResponseStatusDetail> details) {
        return details.stream()
                .map(this::getAsString)
                .collect(Collectors.joining(", "));
    }

    private String getAsString(DtoValidationResponseStatusDetail detail) {
        return "{ "
                + "code='" + detail.getCode() + '\''
                + ", description='" + detail.getDescription() + '\''
                + ", fieldName='" + detail.getFieldName() + '\''
                + ", validationRuleName='" + detail.getValidationRuleName() + '\''
                + " }";
    }
}
