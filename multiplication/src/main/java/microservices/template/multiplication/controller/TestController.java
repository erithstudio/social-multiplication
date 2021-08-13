package microservices.template.multiplication.controller;

import microservices.template.multiplication.helper.Traceable;
import microservices.template.multiplication.service.RegistrationEmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
public class TestController {

    @Autowired
    RegistrationEmailService emailService;

    @GetMapping("/test")
    @Traceable
    public String test(HttpServletRequest servletRequest) {
        emailService.sendConfirmation("name", "link", "email");
        return "success";
    }
}