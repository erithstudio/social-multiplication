package microservices.template.multiplication.helper;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RestController;
import org.thepavel.icomponent.Handler;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Retention(RUNTIME)
@Target(TYPE)
@RestController
@Handler("gameControllerMethodHandler")
public @interface GameController {
}