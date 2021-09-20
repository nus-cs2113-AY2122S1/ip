package duke;

/**
 * This class deals with input from users and tries to make sense of it
 */
public class Parser {

    /**
     * Process input from user for the first word of the input
     * The first word usually contains crucial information such as task types or actions
     *
     * @param input input from user
     * @return first word from the user input
     */
    public String getPrefix(String input) {
        String[] inputWords = input.split(" ");
        return inputWords[0].toLowerCase();
    }

    /**
     * Strips the first word from the user input
     *
     * @param input input from user
     * @return the remaining content from the user other than the first word
     */
    public String getTaskBody(String input) {
        String[] inputWords = input.split(" ");
        return inputWords[1];
    }

    /**
     * Get the description for the task (only used when it is a deadline or event separated by '/')
     *
     * @param input input from user
     * @return task description from the input task
     */
    public String getTaskDescription(String input) {
        String[] separatedInput = input.split("/");
        return separatedInput[0].trim();
    }

    /**
     * Get the date for the task (only used when it is a deadline or event separated by "/")
     *
     * @param input input from user
     * @return task date from the input task (deadline or event date)
     */
    public String getTaskDate(String input) {
        String[] separatedInput = input.split("/", 2);
        return separatedInput[1].trim();
    }

    /**
     * Count for the number of words in the user input
     *
     * @param input input from user
     * @return number of words in the input
     */
    public int getInputWordCount(String input) {
        return input.split(" ").length;
    }

    /**
     * Extracts the number from the user input (used in delete or done commands)
     *
     * @param input input from user
     * @return number included in the user input
     */
    public int getInputIndex(String input) {
        return Integer.parseInt(input.split(" ", 2)[1].trim());
    }

    /**
     * Checks for situations where there is only a command but no content after it
     *
     * @param input input from user
     * @return true if the user only entered the command without further content, false otherwise
     */
    public boolean isNoTaskDescription(String input) {
        String[] inputWords = getTaskDescription(input).split(" ");
        return inputWords.length == 1;
    }

    /**
     * Check for situations where user did not input a date or time when required
     *
     * @param input input from user
     * @return true if user input is missing a date or time, false otherwise
     */
    public boolean isNoDate(String input) {
        String[] inputWords = input.split(" ", 2)[1].split("/");
        return inputWords.length <= 1;
    }

    /**
     * Checks whether the separator "/" is missing in the user input
     *
     * @param input input from user
     * @return true if the separator is missing, false otherwise
     */
    public boolean isNoSeparator(String input) {
        return !input.contains("/");
    }

    /**
     * Checks for whether the input task is in the wrong format
     * An input task can be wrong in format if it falls under any of the following situations:
     * 1. Deadline or event without a "/"
     * 2. Deadline or event without a task description
     * 3. Deadline or event without a date
     *
     * @param input input from user
     * @return true if the input task is in the wrong format, false otherwise
     */
    public boolean isWrongFormat(String input) {
        boolean isNoSeparator = isNoSeparator(input);
        boolean isNoTask = isNoTaskDescription(input);
        boolean isNoDate = isNoDate(input);
        return isNoSeparator || isNoTask || isNoDate;
    }

}
