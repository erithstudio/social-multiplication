package microservices.template.multiplication.domain;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class DtoAccountBalanceResponse extends ADtoResponse {
    private DtoAccountRequest accountRequest;
    private BigDecimal amount;
    private String currency;
}
