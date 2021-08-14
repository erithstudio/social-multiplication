package microservices.template.multiplication.service.handler.method;

public interface IMethodHandler {
    Object handle(Object executorObject, Object[] arguments) throws Exception;
}
