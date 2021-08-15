package microservices.template.multiplication.processor.executor;

import lombok.extern.slf4j.Slf4j;
import ma.glasnost.orika.MapperFacade;
import microservices.template.multiplication.domain.DtoAccountBalanceRequest;
import microservices.template.multiplication.domain.DtoAccountBalanceResponse;
import microservices.template.multiplication.domain.DtoAccountRequest;
import microservices.template.multiplication.helper.annotation.GameMapper;
import microservices.template.multiplication.mapper.AccountMapper;
import microservices.template.multiplication.processor.ABaseExecutorProcessor;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;

@Slf4j
@GameMapper({AccountMapper.class})
public class FindAccountBalanceExecutor extends ABaseExecutorProcessor<DtoAccountBalanceResponse, DtoAccountRequest> {

    protected FindAccountBalanceExecutor(MapperFacade mapperFacade) {
        super(mapperFacade);
    }

    @Override
    public DtoAccountBalanceResponse process(HttpServletRequest servletRequest, DtoAccountRequest request) {
        request.setContent("cihui");
        DtoAccountBalanceRequest balanceRequest = getMapperFacade().map(request, DtoAccountBalanceRequest.class);
        balanceRequest.setAmount(new BigDecimal(50000));
        balanceRequest.setCurrency("IDR");
        DtoAccountBalanceResponse response = getMapperFacade().map(balanceRequest, DtoAccountBalanceResponse.class);
        return response;
    }
}
