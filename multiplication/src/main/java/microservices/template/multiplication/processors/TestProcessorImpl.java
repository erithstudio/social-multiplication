package microservices.template.multiplication.processors;

import org.springframework.stereotype.Component;

@Component
public class TestProcessorImpl {
    public boolean authorize(String token) {
        System.out.println("param: " + token);
        // implemnt jwt or any any token based authorization logic
        return true;
    }
}
