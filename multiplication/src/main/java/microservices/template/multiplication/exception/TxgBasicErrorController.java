package microservices.template.multiplication.exception;

import microservices.template.multiplication.dto.DtoResponseStatus;
import microservices.template.multiplication.dto.DtoResponseStatusDetail;
import microservices.template.multiplication.dto.DtoValidationResponseStatusDetail;
import microservices.template.multiplication.processor.IModuleProperties;
import microservices.template.multiplication.processor.TxgException;
import microservices.template.multiplication.processor.ValidationProcessorException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.nio.file.AccessDeniedException;
import java.nio.file.ProviderNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

import static java.util.Collections.EMPTY_LIST;
import static microservices.template.multiplication.enumeration.ErrorConstantsEnum.INTERNAL_ERROR;
import static microservices.template.multiplication.enumeration.ErrorConstantsEnum.VALIDATION_ERROR;
import static org.springframework.http.HttpStatus.*;
import static org.springframework.util.CollectionUtils.isEmpty;

@SuppressWarnings({"unchecked", "WeakerAccess"})
@ControllerAdvice
public class TxgBasicErrorController extends ResponseEntityExceptionHandler implements ITxgErrorBasicController {

    private final Logger txglogger = LoggerFactory.getLogger(TxgBasicErrorController.class);

    private final String validationCode;

    private final String internalCode;

    private final IModuleProperties moduleProperties;

    public TxgBasicErrorController(IModuleProperties moduleProperties) {
        this.moduleProperties = moduleProperties;
        validationCode = moduleProperties.errorCode(VALIDATION_ERROR);
        internalCode = moduleProperties.errorCode(INTERNAL_ERROR);
    }

    @SuppressWarnings("NullableProblems")
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        String message = ex.getMessage();
        List<ObjectError> errors = ex.getBindingResult().getAllErrors();
        DtoResponseStatus responseStatus;
        if (!isEmpty(errors)) {
            responseStatus = createResponseStatusDto(processValidationInputErrors(errors, message));
        } else {
            responseStatus = createResponseStatusDto(processValidationInputErrors(EMPTY_LIST, message));

        }

        logError(ex, status, responseStatus);
        return new ResponseEntity<>(responseStatus, status);
    }

    // XXX - if the validated @RequestBody argument of controller method is a List then validation errors are
    // handled by this method instead of handleMethodArgumentNotValid()
    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<DtoResponseStatus> handleConstraintViolationException(ConstraintViolationException ex) {
        Set<ConstraintViolation<?>> errors = ex.getConstraintViolations();
        DtoResponseStatus responseStatus;
        if (!isEmpty(errors)) {
            responseStatus = createResponseStatusDto(processValidationInputErrors(errors));
        } else {
            responseStatus = createResponseStatusDto(processValidationInputErrors(EMPTY_LIST, ex.getMessage()));
        }

        logError(ex, BAD_REQUEST, responseStatus);
        return new ResponseEntity<>(responseStatus, BAD_REQUEST);
    }

    @SuppressWarnings("NullableProblems")
    @Override
    protected ResponseEntity<Object> handleMissingServletRequestParameter(MissingServletRequestParameterException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        DtoResponseStatus responseStatus = createResponseStatusDto(
                new DtoResponseStatusDetail(getInternalCode(), ex.getMessage()));
        logError(ex, status, responseStatus);
        return new ResponseEntity<>(responseStatus, BAD_REQUEST);
    }

    @ExceptionHandler(TxgException.class)
    public ResponseEntity<DtoResponseStatus> handleTxgException(TxgException ex) {
        return processResponse(ex, BAD_REQUEST, createResponseStatusDto(createResponseDetails(ex)));
    }


    @ExceptionHandler(ValidationProcessorException.class)
    public ResponseEntity<DtoResponseStatus> handleValidationProcessorException(ValidationProcessorException ex) {
        return processResponse(ex, BAD_REQUEST, createResponseStatusDto(ex.getDetails()));
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<DtoResponseStatus> handleMethodArgumentTypeMismatchException(MethodArgumentTypeMismatchException ex) {
        return processResponse(ex, BAD_REQUEST, createResponseStatusDto(
                new DtoResponseStatusDetail(getInternalCode(), ex.getMessage())));
    }

    @SuppressWarnings("NullableProblems")
    @Override
    protected ResponseEntity<Object> handleExceptionInternal(Exception ex, Object body, HttpHeaders headers, HttpStatus status, WebRequest request) {
        DtoResponseStatus responseStatus = createResponseStatusDto(
                new DtoResponseStatusDetail(getInternalCode(), ex.getCause().getMessage()));
        logError(ex, status, responseStatus);
        return new ResponseEntity<>(responseStatus, headers, INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<DtoResponseStatus> handleAccessDeniedException(AccessDeniedException ex) {
        return processResponse(ex, FORBIDDEN, createResponseStatusDto(
                new DtoResponseStatusDetail(getInternalCode(), ex.getMessage())));
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<DtoResponseStatus> handleRuntime(RuntimeException ex) {
        if(ex.getCause() != null && ex.getCause() instanceof ValidationProcessorException) {
            return handleValidationProcessorException((ValidationProcessorException) ex.getCause());
        }
        return processResponse(ex, INTERNAL_SERVER_ERROR, createResponseStatusDto(
                new DtoResponseStatusDetail(getInternalCode(), ex.getMessage())));
    }

    @ExceptionHandler(ProviderNotFoundException.class)
    public ResponseEntity<DtoResponseStatus> handleAuthenticationProvider(ProviderNotFoundException ex) {
        return processResponse(ex, UNAUTHORIZED, createResponseStatusDto(
                new DtoResponseStatusDetail(getInternalCode(), ex.getMessage())));
    }

    protected List<DtoResponseStatusDetail> processValidationInputErrors(List<ObjectError> errors, String message) {
        List<DtoResponseStatusDetail> responseStatusDetails = new ArrayList<>(errors.size());
        if (isEmpty(errors)) {
            responseStatusDetails.add(new DtoResponseStatusDetail(validationCode, message));
        } else {
            errors.forEach(error -> {
                if (error instanceof FieldError) {
                    FieldError fieldError = (FieldError) error;
                    responseStatusDetails.add(new DtoValidationResponseStatusDetail(
                            validationCode, fieldError.getField() + " " + fieldError.getDefaultMessage(),
                            fieldError.getField(), fieldError.getDefaultMessage()));
                } else {
                    responseStatusDetails.add(new DtoValidationResponseStatusDetail(
                            validationCode, error.getObjectName() + " " + error.getDefaultMessage(),
                            error.getObjectName(), error.getDefaultMessage()));
                }
            });
        }
        return responseStatusDetails;
    }

    protected List<DtoResponseStatusDetail> processValidationInputErrors(Set<ConstraintViolation<?>> errors) {
        List<DtoResponseStatusDetail> responseStatusDetails = new ArrayList<>(errors.size());
        errors.forEach(error -> {
            String path = String.valueOf(error.getPropertyPath());
            responseStatusDetails.add(new DtoValidationResponseStatusDetail(
                    validationCode, path + " " + error.getMessage(), path, error.getMessage()));
        });
        return responseStatusDetails;
    }

    private List<DtoResponseStatusDetail> createResponseDetails(TxgException ex) {
        return Arrays.asList(new DtoResponseStatusDetail(moduleProperties.baseCode() + "." + ex.getErrorCode(), ex.getMessage()));
    }

    protected String getInternalCode() {
        return internalCode;
    }

    protected ResponseEntity<DtoResponseStatus> processResponse(
            Exception ex, HttpStatus httpStatus, DtoResponseStatus responseStatusDto) {
        logError(ex, httpStatus, responseStatusDto);
        return new ResponseEntity<>(responseStatusDto, httpStatus);
    }


    @Override
    public Logger getLogger() {
        return txglogger;
    }
}
