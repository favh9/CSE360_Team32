import java.util.regex.Matcher;
import java.util.regex.Pattern;

// This class helps keep email address inputs valid
public class EmailValidator {
    // Regular expression for validating email addresses
    private static final String EMAIL_REGEX = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";

    // We call this method statically to check if an email is valid
    public static boolean isValidEmail(String email) {
        if (email == null) {
            return false; // Return false for null inputs
        }
        Pattern pattern = Pattern.compile(EMAIL_REGEX);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }
}
