/**
 * This class deals with parsing user inputs (making sense of the user command).
 */
public class Parser {

    protected static final int INDEX_COMMAND = 0;
    protected static final int INDEX_TASK_NUMBER = 1;
    protected static final int INDEX_BODY = 1;
    protected static final int INDEX_DATE = 1;
    protected static final int INDEX_DESCRIPTION = 0;
    protected static final int INDEX_DONE_NUMBER = 1;
    protected static final int INDEX_DESCRIPTION_FROM_FILE = 2;
    protected static final int INDEX_IS_DONE_FROM_FILE = 1;
    protected static final int INDEX_DATE_FROM_FILE = 3;


    protected static final String EMPTY = "";

    /**
     * Extract command specified by user input.
     * This is the first word from the user input.
     *
     * @param line String of user input
     * @return command specified by user input
     */
    public static String parseCommand(String line) {
        String[] words = line.trim().split(" ");
        return words[INDEX_COMMAND].trim();
    }

    /**
     * Extract the body of user input, excluding the command part.
     * Command is the first word from the user input.
     *
     * @param line String of user input
     * @return string of user input excluding the command part
     */
    public static String parseBody(String line) {
        String[] words = line.trim().split(" ", 2);
        return words[INDEX_BODY].trim();
    }

    /**
     * Extract the index of task to be marked as done from user input.
     *
     * @param line String of user input
     * @return index of the task to be marked as done
     */
    public static String parseDoneIndex(String line) {
        String[] words = line.trim().split(" ", 2);
        return words[INDEX_DONE_NUMBER].trim();
    }

    /**
     * Check if there is no body in user input.
     * That is, check if there is only a command.
     *
     * @param line String of user input
     * @return true if there is only a command in user input.
     *          false if there is body in user input.
     */
    public static boolean hasNoBody(String line) {
        String[] words = line.trim().split(" ", 2);
        return words.length == 1;
    }

    /**
     * Check if there is no description of Event or Deadline task in user input.
     *
     * @param line String of user input
     * @param separator String indicator that separates description and date for Event or Deadline (e.g., "/on")
     * @return true if there is no description of Event or Deadline
     *          false if there is a description
     */
    public static boolean hasNoDescription(String line, String separator) {
        String[] words = parseBody(line).trim().split(separator);
        return words[INDEX_DESCRIPTION].equals(EMPTY);
    }

    /**
     * Check if there is no description of a Todo task to be added, in user input.
     *
     * @param line String of user input
     * @return true if there is no description of Todo
     *          false if there is a description
     */
    public static boolean hasNoTodoDescription(String line) {
        String[] words = line.trim().split(" ");
        return words.length == 1;
    }

    /**
     * Check if there is no index of the task to be marked as done in user input.
     *
     * @param line String of user input
     * @return true if there is no index of task
     *          false if there is an index
     */
    public static boolean hasNoDoneIndex(String line) {
        String[] words = line.trim().split("done");
        return words.length == 0;
    }

    /**
     * Check if there is no date for a Deadline/Event to be added, from user input.
     *
     * @param line String of user input
     * @return true if there is no date
     *          false if there is a date
     */
    public static boolean hasNoDate(String line, String separator) {
        String[] words = parseBody(line).split(separator);
        return words.length == 1;
    }

    /**
     * Extract the date of a Deadline/Event to be added, from user input
     *
     * @param line String of user input
     * @param separator String indicator that separates description and date for Event or Deadline (e.g., "/on")
     * @return date of Deadline/Event
     */
    public static String parseDate(String line, String separator) {
        String[] words = parseBody(line).split(separator);
        return words[INDEX_DATE].trim();
    }

    /**
     * Extract the description of a Deadline/Event to be added, from user input
     *
     * @param line String of user input
     * @param separator String indicator that separates description and date for Event or Deadline (e.g., "/on")
     * @return description of Deadline/Event
     */
    public static String parseDescription(String line, String separator) {
        String[] words = parseBody(line).split(separator);
        return words[INDEX_DESCRIPTION].trim();
    }

    /**
     * Extract the Task type from saved data file.
     *
     * @param line A line from the saved data file
     * @return Task type
     */
    public static String parseCommandFromFile(String line) {
        String[] words = line.trim().split(Storage.SPACER);
        return words[INDEX_COMMAND].trim();
    }

    /**
     * Extract the Task description from saved data file.
     *
     * @param line A line from the saved data file
     * @return Task description
     */
    public static String parseDescriptionFromFile(String line) {
        String[] words = line.trim().split(Storage.SPACER);
        return words[INDEX_DESCRIPTION_FROM_FILE].trim();
    }

    /**
     * Extract the Task date from saved data file.
     *
     * @param line A line from the saved data file
     * @return Task date
     */
    public static String parseDateFromFile(String line) {
        String[] words = line.trim().split(Storage.SPACER);
        return words[INDEX_DATE_FROM_FILE].trim();
    }

    /**
     * Extract the Task done indicator from saved data file.
     *
     * @param line A line from the saved data file
     * @return Indicator of whether the Task is done or not
     */
    public static String parseIsDoneFromFile(String line) {
        String[] words = line.trim().split(Storage.SPACER);
        return words[INDEX_IS_DONE_FROM_FILE].trim();
    }
}
