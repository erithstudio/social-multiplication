package microservices.template.multiplication.dto;


import microservices.template.multiplication.enumeration.DtoResponseStatusCodeEnum;

public abstract class ADtoResponse implements IDtoResponse {

    private DtoResponseStatusCodeEnum statusCode;

    public ADtoResponse(DtoResponseStatusCodeEnum statusCode) {
        this.statusCode = statusCode;
    }

    public ADtoResponse() {
    }

    @Override
    public DtoResponseStatusCodeEnum getStatusCode() {
        return statusCode;
    }

    @Override
    public void setStatusCode(DtoResponseStatusCodeEnum statusCode) {
        this.statusCode = statusCode;
    }

}
