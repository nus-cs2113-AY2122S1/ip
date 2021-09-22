package exceptions;

public class DateFormatException extends CommandSyntaxException {

    public static final String INVALID_CAUSE =
            "Unable to parse DateTime Format! Type 'dates' to see formats.";

    public DateFormatException() {
        super("DateFormatException: " + INVALID_CAUSE);
    }
}
