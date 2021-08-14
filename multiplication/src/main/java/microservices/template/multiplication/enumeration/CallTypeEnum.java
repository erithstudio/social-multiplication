package microservices.template.multiplication.enumeration;

public enum CallTypeEnum {
    CALL("CALL"),
    DOWNLOAD("DOWNLOAD"),
    SEARCH("SEARCH"),
    UPDATE("UPDATE"),
    CREATE("CREATE"),
    VALIDATE("VALIDATE");

    private String value;

    CallTypeEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
