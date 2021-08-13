package microservices.template.multiplication.helper;

import microservices.template.multiplication.processors.TestProcessorImpl;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;

@Aspect
public class ExecutorAspect {

    @Autowired
    TestProcessorImpl authBean;

    @Before("@annotation(microservices.template.multiplication.helper.Executor) && args(request,..)")
    public void before(HttpServletRequest request) {
        if (!(request instanceof HttpServletRequest)) {
            throw new RuntimeException("request should be HttpServletRequesttype");
        }

        if (authBean.authorize(request.getHeader("Authorization"))) {
            request.setAttribute(
                    "userSession",
                    "session information which cann be acces in controller"
            );
        } else {
            throw new RuntimeException("auth error..!!!");
        }

    }
}
