package microservices.template.multiplication.processor.executor;

import lombok.extern.slf4j.Slf4j;
import ma.glasnost.orika.MapperFacade;
import microservices.template.multiplication.domain.DtoAccountBalanceRequest;
import microservices.template.multiplication.domain.DtoAccountBalanceResponse;
import microservices.template.multiplication.domain.DtoAccountRequest;
import microservices.template.multiplication.helper.annotation.GameMapper;
import microservices.template.multiplication.helper.annotation.GameValidator;
import microservices.template.multiplication.mapper.AccountMapper;
import microservices.template.multiplication.processor.ABaseExecutorProcessor;
import microservices.template.multiplication.processor.IModuleProperties;
import microservices.template.multiplication.processor.ValidationDetailsReport;
import microservices.template.multiplication.processor.ValidationProcessorException;
import microservices.template.multiplication.processor.validator.AccountCurrencyExistValidator;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;

@Slf4j
@GameMapper({AccountMapper.class})
@GameValidator({AccountCurrencyExistValidator.class})
public class FindAccountBalanceExecutor extends ABaseExecutorProcessor<DtoAccountBalanceResponse, DtoAccountRequest> {

    protected FindAccountBalanceExecutor(MapperFacade mapperFacade, IModuleProperties moduleProperties) {
        super(mapperFacade, moduleProperties);
    }

    @Override
    public DtoAccountBalanceResponse process(HttpServletRequest servletRequest, DtoAccountRequest request) throws ValidationProcessorException {
        request.setContent("cihui");
        DtoAccountBalanceRequest balanceRequest = getMapperFacade().map(request, DtoAccountBalanceRequest.class);

        new ValidationDetailsReport(getValidators()).with(getModuleProperties()).validate(servletRequest, balanceRequest);

        balanceRequest.setAmount(new BigDecimal(50000));
        balanceRequest.setCurrency("IDR");
        DtoAccountBalanceResponse response = getMapperFacade().map(balanceRequest, DtoAccountBalanceResponse.class);
        return response;
    }
}
