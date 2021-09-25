package common;

public class Error {
    public static final String READ_IOEXCEPTION = "  (!) FATAL: Could not process data file, starting with empty data";
    public static final String WRITE_IOEXCEPTION = "  (!) FATAL: IO Error";
    public static final String FILE_NOT_EXIST = "  (!) Data file not found" + System.lineSeparator() +
            "  (+) Empty data file created: %s";

    public static final String ERROR_FORMAT_TODO = "  (!) Todo description cannot be empty!" + System.lineSeparator() +
            "  (!) Format: /todo <description>";
    public static final String ERROR_FORMAT_DEADLINE = "  (!) Invalid/missing values" + System.lineSeparator() +
            "  (!) Format: /deadline <description> -by <date: dd/mm/yyyy hhmm>";
    public static final String ERROR_FORMAT_EVENT = "  (!) Invalid/missing values" + System.lineSeparator() +
            "  (!) Format: /event <description> -from <date: dd/mm/yyyy hhmm> -to <date: dd/mm/yyyy hhmm>";
    public static final String ERROR_FORMAT_DONE = "  (!) Task ID cannot be empty!" + System.lineSeparator() +
            "  (!) Format: /done <id>";
    public static final String ERROR_FORMAT_DELETE = "  (!) Task ID cannot be empty!" + System.lineSeparator() +
            "  (!) Format: /delete <id>";
    public static final String ERROR_FORMAT_FIND = "  (!) Search term cannot be empty!" + System.lineSeparator() +
            "  (!) Format: /find <search_term>";
    public static final String ERROR_INVALID_TASK = "  (!) Task ID does not exist >:(";
    public static final String ERROR_INVALID_COMMAND = "  (!) Unrecognised Command! ";
}