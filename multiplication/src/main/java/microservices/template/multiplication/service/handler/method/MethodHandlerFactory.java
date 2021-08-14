package microservices.template.multiplication.service.handler.method;

import microservices.template.multiplication.enumeration.CallTypeEnum;
import microservices.template.multiplication.service.handler.method.impl.*;

import java.util.HashMap;
import java.util.Map;

public class MethodHandlerFactory {
    private Map<CallTypeEnum, IMethodHandler> callerMap = new HashMap<>();

    private static final MethodHandlerFactory SINGLETON = new MethodHandlerFactory();

    private MethodHandlerFactory() {
        super();
    }

    public static MethodHandlerFactory getInstance() {
        return SINGLETON;
    }

    public Object execute(CallTypeEnum type, Object executorObject, Object[] arguments) throws Exception {
        if (!callerMap.containsKey(type)) {
            switch (type) {
                case CALL:
                    callerMap.put(CallTypeEnum.CALL, new CallMethodHandler());
                    break;
                case DOWNLOAD:
                    callerMap.put(CallTypeEnum.DOWNLOAD, new DownloadlMethodHandler());
                    break;
                case SEARCH:
                    callerMap.put(CallTypeEnum.SEARCH, new SearchMethodHandler());
                    break;
                case UPDATE:
                    callerMap.put(CallTypeEnum.UPDATE, new UpdateMethodHandler());
                    break;
                case CREATE:
                    callerMap.put(CallTypeEnum.CREATE, new CreateMethodHandler());
                    break;
                case VALIDATE:
                    callerMap.put(CallTypeEnum.VALIDATE, new ValidateMethodHandler());
                    break;
                default:
                    throw new RuntimeException();
            }
        }
        return callerMap.get(type).handle(executorObject, arguments);
    }
}
