package microservices.template.multiplication.helper;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class CallHandlerUtils {

    private static final String REGEX_GROUP_TYPENAME = "^(?:[a-z]*[^A-Z])+";
    private static final String REGEX_GROUP_EXECUTOR_ID = "(?:[A-Z][a-z]+)+";

    private static final String getValueByRegex(String words, String regex) {
        Pattern r = Pattern.compile(regex);
        Matcher m = r.matcher(words);
        return m.find() ? m.group(0) : words;
    }

    public static final String getTypeName(String words) {
        return getValueByRegex(words, REGEX_GROUP_TYPENAME);
    }

    public static final String getExecutorId(String words) {
        return getValueByRegex(words, REGEX_GROUP_EXECUTOR_ID);
    }
}
