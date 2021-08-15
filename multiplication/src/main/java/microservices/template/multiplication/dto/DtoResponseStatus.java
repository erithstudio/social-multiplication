package microservices.template.multiplication.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import java.util.List;

@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class DtoResponseStatus extends ADtoResponse {

    private String traceId;

    private List<DtoResponseStatusDetail> responseStatusDetails;

    public DtoResponseStatus() {
    }

    public DtoResponseStatus(List<DtoResponseStatusDetail> responseStatusDetails) {
        this.responseStatusDetails = responseStatusDetails;
    }

    public List<DtoResponseStatusDetail> getResponseStatusDetails() {
        return responseStatusDetails;
    }

    public void setResponseStatusDetails(List<DtoResponseStatusDetail> responseStatusDetails) {
        this.responseStatusDetails = responseStatusDetails;
    }

    public String getTraceId() {
        return traceId;
    }

    public void setTraceId(String traceId) {
        this.traceId = traceId;
    }
}
