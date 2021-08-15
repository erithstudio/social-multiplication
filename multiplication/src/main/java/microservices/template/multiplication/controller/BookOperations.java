package microservices.template.multiplication.controller;

import microservices.template.multiplication.domain.DtoAccountBalanceResponse;
import microservices.template.multiplication.domain.DtoAccountRequest;
import microservices.template.multiplication.helper.annotation.GameController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@GameController
@RequestMapping(path = "/BookOperations")
public interface BookOperations {
    @GetMapping("/")
    DtoAccountBalanceResponse callFindAccountBalanceExecutor(HttpServletRequest servletRequest, DtoAccountRequest dtoAccountBalanceRequest);
}
