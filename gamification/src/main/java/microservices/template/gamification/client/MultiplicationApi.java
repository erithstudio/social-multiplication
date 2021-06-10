package microservices.template.gamification.client;

import microservices.template.gamification.client.dto.MultiplicationResultAttempt;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(name = "multiplication")
public interface MultiplicationApi {
    @RequestMapping(method = RequestMethod.GET, value = "/results/{id}")
    MultiplicationResultAttempt retrieveMultiplicationResultAttemptbyId(@PathVariable("id") Long id);
}
