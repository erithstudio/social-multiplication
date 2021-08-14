package microservices.template.multiplication.processor;

import microservices.template.multiplication.domain.DtoAccountBalanceRequest;

import javax.servlet.http.HttpServletRequest;

public interface IBaseProcessor<R, T> {

    R process(HttpServletRequest servletRequest, T request) throws Exception;
}
