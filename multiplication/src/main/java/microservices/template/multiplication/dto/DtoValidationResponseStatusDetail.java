package microservices.template.multiplication.dto;

public class DtoValidationResponseStatusDetail extends DtoResponseStatusDetail {

    private String fieldName;
    private String validationRuleName;

    public String getFieldName() {
        return fieldName;
    }

    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }

    public String getValidationRuleName() {
        return validationRuleName;
    }

    public void setValidationRuleName(String validationRuleName) {
        this.validationRuleName = validationRuleName;
    }

    public DtoValidationResponseStatusDetail(String fieldName, String validationRuleName) {
        this.fieldName = fieldName;
        this.validationRuleName = validationRuleName;
    }

    public DtoValidationResponseStatusDetail(String code, String description, String fieldName, String validationRuleName) {
        super(code, description);
        this.fieldName = fieldName;
        this.validationRuleName = validationRuleName;
    }

    public DtoValidationResponseStatusDetail() {
    }
}
