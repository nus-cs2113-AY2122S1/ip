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
    protected static final String DEADLINE_DATE_SEPARATOR = "/by";
    protected static final String EVENT_DATE_SEPARATOR = "/on";

    public static String parseCommand(String line) {
        String[] words = line.trim().split(" ");
        return words[INDEX_COMMAND].trim();
    }

    public static String parseBody(String line) {
        String[] words = line.trim().split(" ", 2);
        return words[INDEX_BODY].trim();
    }

    public static String parseDoneIndex(String line) {
        String[] words = line.trim().split(" ", 2);
        return words[INDEX_DONE_NUMBER].trim();
    }

    public static boolean hasNoBody(String line) {
        String[] words = line.trim().split(" ", 2);
        return words.length == 1;
    }

    public static boolean hasNoDescription(String line, String separator) {
        String[] words = parseBody(line).trim().split(separator);
        return words[INDEX_DESCRIPTION].equals(EMPTY);
    }

    public static boolean hasNoTodoDescription(String line) {
        String[] words = line.trim().split(" ");
        return words.length == 1;
    }

    public static boolean hasNoDoneIndex(String line) {
        String[] words = line.trim().split("done");
        return words.length == 0;
    }

    public static boolean hasNoDeadlineDate(String line) {
        String[] words = parseBody(line).split(DEADLINE_DATE_SEPARATOR);
        return words.length == 1;
    }

    public static boolean hasNoEventDate(String line) {
        String[] words = parseBody(line).split(EVENT_DATE_SEPARATOR);
        return words.length == 1;
    }

    public static int parseLengthOfLine(String line) {
        String[] words = line.trim().split(" ");
        return words.length;
    }

    public static String parseEventDate(String line) {
        String[] words = parseBody(line).split(EVENT_DATE_SEPARATOR);
        return words[INDEX_DATE].trim();
    }

    public static String parseDeadlineDate(String line) {
        String[] words = parseBody(line).split(DEADLINE_DATE_SEPARATOR);
        return words[INDEX_DATE].trim();
    }

    public static String parseEventDescription(String line) {
        String[] words = parseBody(line).split(EVENT_DATE_SEPARATOR);
        return words[INDEX_DESCRIPTION].trim();
    }

    public static String parseDeadlineDescription(String line) {
        String[] words = parseBody(line).split(DEADLINE_DATE_SEPARATOR);
        return words[INDEX_DESCRIPTION].trim();
    }

    public static String parseCommandFromFile(String line) {
        String[] words = line.split(Storage.SPACER);
        return words[INDEX_COMMAND].trim();
    }

    public static String parseDescriptionFromFile(String line) {
        String[] words = line.split(Storage.SPACER);
        return words[INDEX_DESCRIPTION_FROM_FILE].trim();
    }

    public static String parseDateFromFile(String line) {
        String[] words = line.split(Storage.SPACER);
        return words[INDEX_DATE_FROM_FILE].trim();
    }

    public static String parseIsDoneFromFile(String line) {
        String[] words = line.split(Storage.SPACER);
        return words[INDEX_IS_DONE_FROM_FILE].trim();
    }
}
