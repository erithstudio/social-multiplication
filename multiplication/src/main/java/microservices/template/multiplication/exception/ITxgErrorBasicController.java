package microservices.template.multiplication.exception;

import microservices.template.multiplication.dto.DtoResponseStatus;
import microservices.template.multiplication.dto.DtoResponseStatusDetail;
import microservices.template.multiplication.enumeration.DtoResponseStatusCodeEnum;
import org.slf4j.Logger;
import org.springframework.http.HttpStatus;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public interface ITxgErrorBasicController {

    Logger getLogger();

    default DtoResponseStatus createResponseStatusDto(DtoResponseStatusDetail responseStatusDetail) {
        return createResponseStatusDto(List.of(responseStatusDetail));
    }

    default <D extends DtoResponseStatusDetail> DtoResponseStatus createResponseStatusDto(List<D> responseStatusDetails) {
        return createResponseStatusDto(DtoResponseStatusCodeEnum.ERROR, responseStatusDetails);
    }

    @SuppressWarnings("SameParameterValue")
    default <D extends DtoResponseStatusDetail> DtoResponseStatus createResponseStatusDto(DtoResponseStatusCodeEnum statusCode, List<D> responseStatusDetails) {
        DtoResponseStatus responseStatus = new DtoResponseStatus();
        responseStatus.setStatusCode(statusCode);
        responseStatus.setResponseStatusDetails((List<DtoResponseStatusDetail>) responseStatusDetails);
        responseStatus.setTraceId(UUID.randomUUID().toString());
        return responseStatus;
    }

    default void logError(Exception ex, HttpStatus httpStatus, DtoResponseStatus responseStatusDto) {
        String errorCodes = responseStatusDto.getResponseStatusDetails().stream()
                .map(d -> d.getCode()).collect(Collectors.joining(", ", "[", "]"));

        getLogger().error(
                "Caught exception, traceId {} , code {}, status {}", responseStatusDto.getTraceId(), errorCodes, httpStatus, ex);
    }
}
