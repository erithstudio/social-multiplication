package microservices.template.multiplication.domain;

import lombok.Data;

@Data
public class DtoAccountBalanceRequest {
    private String accountId;
    private String content;
}
