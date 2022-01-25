package utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EmailValidator {
    private static final String EMAIL_PATTERN = "[A-Za-z0-9+_.-]+@.+";
    private static final Pattern pattern = Pattern.compile(EMAIL_PATTERN);

    public static boolean emailValidate(final String hex) {
        Matcher matcher = pattern.matcher(hex);
        return matcher.matches();
    }
}
