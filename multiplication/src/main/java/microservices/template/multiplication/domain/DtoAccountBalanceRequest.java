package microservices.template.multiplication.domain;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class DtoAccountBalanceRequest {
    private DtoAccountRequest accountRequest;
    private BigDecimal amount;
    private String currency;
}
