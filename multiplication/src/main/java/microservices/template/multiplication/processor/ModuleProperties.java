package microservices.template.multiplication.processor;

public class ModuleProperties implements IModuleProperties {

    private final IServiceProperties serviceProperties;
    private final String moduleNumber;

    public ModuleProperties(IServiceProperties serviceProperties, String moduleNumber) {
        this.serviceProperties = serviceProperties;
        this.moduleNumber = moduleNumber;
    }

    @Override
    public String getServiceNumber() {
        return serviceProperties.getServiceNumber();
    }

    @Override
    public String getModuleNumber() {
        return moduleNumber;
    }
}
