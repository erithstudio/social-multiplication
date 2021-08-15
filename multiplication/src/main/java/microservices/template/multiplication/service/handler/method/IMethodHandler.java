package microservices.template.multiplication.service.handler.method;

public interface IMethodHandler {
    Object handle(String objectId, Object[] arguments) throws Exception;
}
