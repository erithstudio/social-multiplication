package microservices.template.multiplication.service;

import lombok.extern.slf4j.Slf4j;
import microservices.template.multiplication.domain.Client;
import microservices.template.multiplication.helper.GameExecutor;
import microservices.template.multiplication.helper.Param;
import microservices.template.multiplication.helper.To;
import org.springframework.core.annotation.MergedAnnotations;
import org.springframework.stereotype.Component;
import org.thepavel.icomponent.handler.MethodHandler;
import org.thepavel.icomponent.metadata.MethodMetadata;
import org.thepavel.icomponent.metadata.ParameterMetadata;

import java.beans.Introspector;
import java.util.*;

@Slf4j
@Component
public class GameControllerMethodHandler implements MethodHandler {
    @Override
    public Object handle(Object[] arguments, MethodMetadata methodMetadata) {
        // Get the method name and cut off "send"
        // Eg: "sendWelcome" -> "Welcome"
        String emailType = methodMetadata.getSourceMethod().getName().substring(4);

        String valueGameExecutor = methodMetadata.getAnnotations().get(GameExecutor.class).getString("value");

        // "Welcome" -> "welcome"
        String templateName = Introspector.decapitalize(emailType);

        // "welcome" -> "email.subject.welcome"
        String subjectKeyInMessageBundle = "email.subject." + templateName;

        // Get metadata of the method parameters
        List<ParameterMetadata> parametersMetadata = methodMetadata.getParametersMetadata();

        // Collections to populate
        Map<String, Object> templateParameters = new HashMap<>();
        Set<String> emails = new HashSet<>();

        for (int i = 0; i < parametersMetadata.size(); i++) {
            // Get annotations of the i-th method parameter
            MergedAnnotations parameterAnnotations = parametersMetadata.get(i).getAnnotations();
            Object parameterValue = arguments[i];

            // Populate templateParameters
            if (parameterAnnotations.isPresent(Param.class)) {
                // Get the Param annotation then get the value of its `value` attribute as String
                String templateParameterName = parameterAnnotations.get(Param.class).getString("value");
                templateParameters.put(templateParameterName, parameterValue);
            }

            // Populate emails
            if (parameterAnnotations.isPresent(To.class)) {
                if (parameterValue instanceof String) {
                    emails.add((String) parameterValue);
                } else if (parameterValue instanceof Client) {
                    Client client = (Client) parameterValue;
                    emails.add(client.getEmail());
                }
            }
        }

        log.error(String.format("%s : %s : %s", subjectKeyInMessageBundle, templateName, templateParameters));

        return valueGameExecutor;
    }
}