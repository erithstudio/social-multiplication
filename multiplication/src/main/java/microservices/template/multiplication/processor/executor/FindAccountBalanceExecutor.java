package microservices.template.multiplication.processor.executor;

import lombok.extern.slf4j.Slf4j;
import microservices.template.multiplication.domain.DtoAccountBalanceRequest;
import microservices.template.multiplication.domain.DtoAccountBalanceResponse;
import microservices.template.multiplication.processor.IExecutorProcessor;

import javax.servlet.http.HttpServletRequest;

@Slf4j
public class FindAccountBalanceExecutor implements IExecutorProcessor<DtoAccountBalanceResponse, DtoAccountBalanceRequest> {

    @Override
    public DtoAccountBalanceResponse process(HttpServletRequest servletRequest, DtoAccountBalanceRequest request) {
        DtoAccountBalanceResponse response = new DtoAccountBalanceResponse();
        response.setAccountId(request.getAccountId());
        response.setContent("yuhuuuii..!!");
        return response;
    }
}
