package microservices.template.multiplication.processor;

public class ServiceProperties implements IServiceProperties {

    private final String serviceNumber;

    public ServiceProperties(String serviceNumber) {
        this.serviceNumber = serviceNumber;
    }

    @Override
    public String getServiceNumber() {
        return serviceNumber;
    }
}
