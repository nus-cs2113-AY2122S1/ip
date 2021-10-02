package exceptions;

public class DateFormatException extends CommandSyntaxException {

    public static final String INVALID_CAUSE =
            "Unable to parse DateTime Format! Type 'dates' to see formats.";

    /**
     * Exception when the date format given by the user has an invalid format
     * E.g. "Sunday" cannot be parsed by the internal parser
     * Valid formats can be found by typing 'dates'
     */
    public DateFormatException() {
        super("DateFormatException: " + INVALID_CAUSE);
    }
}
