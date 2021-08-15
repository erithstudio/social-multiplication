package microservices.template.multiplication.domain;

import lombok.Data;
import microservices.template.multiplication.enumeration.DtoResponseStatusCodeEnum;

@Data
public class ADtoResponse {
    private DtoResponseStatusCodeEnum statusCode;
}
