package microservices.template.multiplication.dto;

import microservices.template.multiplication.enumeration.DtoResponseStatusCodeEnum;

public interface IDtoResponse {

    DtoResponseStatusCodeEnum getStatusCode();

    void setStatusCode(DtoResponseStatusCodeEnum statusCode);

}
