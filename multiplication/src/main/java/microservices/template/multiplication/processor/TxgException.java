package microservices.template.multiplication.processor;

public class TxgException extends Exception {
    private String errorCode;
    private String messageTemplate;
    private String field;
    private String validationRule;
    private String[] templateArgs;

    public TxgException() {
        super();
    }

    public TxgException(final String message, final Throwable cause) {
        super(message, cause);
    }

    public TxgException(final String message) {
        super(message);
    }

    public TxgException(final Throwable cause) {
        super(cause);
    }

    public TxgException(String errorCode, String message) {
        super(message);
        this.errorCode = errorCode;
    }

    public TxgException(String errorCode, String messageTemplate, String field, String validationRule) {
        super(String.format(messageTemplate, field));
        this.errorCode = errorCode;
        this.messageTemplate = messageTemplate;
        this.field = field;
        this.validationRule = validationRule;
        this.templateArgs = new String[]{field};
    }

    public TxgException(String errorCode, String messageTemplate, String field, String validationRule, String... templateArgs) {
        super(String.format(messageTemplate, field));
        this.errorCode = errorCode;
        this.messageTemplate = messageTemplate;
        this.field = field;
        this.validationRule = validationRule;
        this.templateArgs = templateArgs;
    }

    public TxgException(String errorCode, String messageTemplate, String field, String validationRule, Throwable cause) {
        super(String.format(messageTemplate, field), cause);
        this.errorCode = errorCode;
        this.messageTemplate = messageTemplate;
        this.field = field;
        this.validationRule = validationRule;
        this.templateArgs = new String[]{field};
    }

    public TxgException(String errorCode, String messageTemplate, String validationRule, String... templateArgs) {
        super(String.format(messageTemplate, (Object[]) templateArgs));
        this.errorCode = errorCode;
        this.messageTemplate = messageTemplate;
        this.validationRule = validationRule;
        this.templateArgs = templateArgs;
    }

    public TxgException(Throwable cause, String errorCode, String messageTemplate, String validationRule, String... templateArgs) {
        super(String.format(messageTemplate, (Object[]) templateArgs), cause);
        this.errorCode = errorCode;
        this.messageTemplate = messageTemplate;
        this.validationRule = validationRule;
        this.templateArgs = templateArgs;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getMessageTemplate() {
        return messageTemplate;
    }

    public void setMessageTemplate(String messageTemplate) {
        this.messageTemplate = messageTemplate;
    }

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    public String getValidationRule() {
        return validationRule;
    }

    public void setValidationRule(String validationRule) {
        this.validationRule = validationRule;
    }

    public String[] getTemplateArgs() {
        return templateArgs;
    }

    public void setTemplateArgs(String[] templateArgs) {
        this.templateArgs = templateArgs;
    }
}
