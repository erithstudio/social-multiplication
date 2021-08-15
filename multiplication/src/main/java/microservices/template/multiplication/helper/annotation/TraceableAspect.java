package microservices.template.multiplication.helper.annotation;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class TraceableAspect {

    @Around("@annotation(microservices.template.multiplication.helper.annotation.Traceable)")
    public Object trace(ProceedingJoinPoint joinPoint) throws Throwable {

        System.out.println("Input :\n" + joinPoint.getArgs()[0]);

        HttpServletRequest servletRequest = (HttpServletRequest) joinPoint.getArgs()[0];

        System.out.println(servletRequest.getRemoteAddr());

        Object result = joinPoint.proceed();

        System.out.println(result);

        return result;
    }

}