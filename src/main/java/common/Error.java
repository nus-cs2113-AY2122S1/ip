package common;

public class Error {
    public static final String ERROR_IOEXCEPTION = "  (!) FATAL: Could not process data file, starting with empty data";
    public static final String ERROR_FORMAT_TODO = "  (!) Todo description cannot be empty!"
            + System.lineSeparator() + "  (!) Format: /todo <description>";
    public static final String ERROR_FORMAT_DEADLINE = "  (!) Invalid/missing values"
            + System.lineSeparator() + "  (!) Format: /deadline <description> -by <date>";
    public static final String ERROR_FORMAT_EVENT = "  (!) Invalid/missing values"
            + System.lineSeparator() + "  (!) Format: /event <description> -from <date> -to <date>";
    public static final String ERROR_FORMAT_TASK = "  (!) Task ID cannot be empty!"
            + System.lineSeparator() + "  (!) Format: /done <id>";
    public static final String ERROR_INVALID_TASK = "  (!) Task ID does not exist >:(";
    public static final String ERROR_INVALID_COMMAND = "  (!) Unrecognised Command! ";
}