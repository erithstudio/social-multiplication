package microservices.template.multiplication.service.handler.method;

import microservices.template.multiplication.enumeration.CallTypeEnum;
import microservices.template.multiplication.service.handler.method.impl.*;
import org.springframework.context.ApplicationContext;

import java.lang.reflect.InvocationTargetException;
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

    public Object execute(CallTypeEnum type, ApplicationContext context, String objectId, Object[] arguments) throws Throwable {
        if (!callerMap.containsKey(type)) {
            switch (type) {
                case CALL:
                    callerMap.put(CallTypeEnum.CALL, new CallMethodHandler(context));
                    break;
                case DOWNLOAD:
                    callerMap.put(CallTypeEnum.DOWNLOAD, new DownloadlMethodHandler(context));
                    break;
                case UPLOAD:
                    callerMap.put(CallTypeEnum.UPLOAD, new UploadMethodHandler(context));
                    break;
                case SEARCH:
                    callerMap.put(CallTypeEnum.SEARCH, new SearchMethodHandler(context));
                    break;
                case UPDATE:
                    callerMap.put(CallTypeEnum.UPDATE, new UpdateMethodHandler(context));
                    break;
                case CREATE:
                    callerMap.put(CallTypeEnum.CREATE, new CreateMethodHandler(context));
                    break;
                case VALIDATE:
                    callerMap.put(CallTypeEnum.VALIDATE, new ValidateMethodHandler(context));
                    break;
                default:
                    throw new RuntimeException();
            }
        }

        Object result = null;
        try {
            result = callerMap.get(type).handle(objectId, arguments);
        } catch (InvocationTargetException ex) {
            throw ex.getCause();
        }
        return result;
    }
}
