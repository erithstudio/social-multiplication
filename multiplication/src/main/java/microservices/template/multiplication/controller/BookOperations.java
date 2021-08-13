package microservices.template.multiplication.controller;

import microservices.template.multiplication.helper.Executor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping(path = "/BookOperations", headers = {"Authorization"})
public interface BookOperations {
    @GetMapping("/")
    @Executor
    String test(HttpServletRequest servletRequest);
}
