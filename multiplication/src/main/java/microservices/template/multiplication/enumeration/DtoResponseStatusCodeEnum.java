package microservices.template.multiplication.enumeration;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public enum DtoResponseStatusCodeEnum {
    ERROR,
    WARNING,
    SUCCESSFUL,
    NOT_FOUND,
    INTERNAL_ERROR;

    public static List<String> getAll() {
        return Arrays.stream(DtoResponseStatusCodeEnum.values())
                .map(Enum::name)
                .collect(Collectors.toList());
    }
}
