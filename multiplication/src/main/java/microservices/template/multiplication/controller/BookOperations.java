package microservices.template.multiplication.controller;

import microservices.template.multiplication.helper.GameController;
import microservices.template.multiplication.helper.GameExecutor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@GameController
@RequestMapping(path = "/BookOperations")
public interface BookOperations {
    @GetMapping("/")
    @GameExecutor(value = "test")
    String test(HttpServletRequest servletRequest);
}
