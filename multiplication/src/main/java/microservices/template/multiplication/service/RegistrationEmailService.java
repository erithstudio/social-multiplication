package microservices.template.multiplication.service;

import microservices.template.multiplication.domain.Client;
import microservices.template.multiplication.helper.EmailService;
import microservices.template.multiplication.helper.Param;
import microservices.template.multiplication.helper.To;

@EmailService
public interface RegistrationEmailService {
    void sendConfirmation(@Param("name") String name, @Param("link") String link, @To String email);

    void sendWelcome(@Param("client") @To Client client);
}
