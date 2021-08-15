package microservices.template.multiplication.domain;

import lombok.Data;

@Data
public class DtoAccountRequest {
    private String accountId;
    private String content;
}
