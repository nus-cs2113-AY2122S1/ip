package duke;

import duke.exception.DukeMissingDataException;

/**
 * Deals with String processing, usually input from users and DukeData.txt.
 */
public class Parser {
    private static final String DELETE_KEYWORD = "delete";
    private static final String DONE_KEYWORD = "done";
    private static final String EVENT_KEYWORD = "event";
    private static final String DEADLINE_KEYWORD = "deadline";
    private static final String TO_DO_KEYWORD = "todo";
    private static final String LIST_KEYWORD = "list";
    private static final String BYE_KEYWORD = "bye";
    private static final int TOTAL_DATA_PARTS = 3;
    private static final int TASK_TYPE_INDEX = 0;
    private static final int DONE_INDEX = 1;
    private static final int DESCRIPTION_INDEX = 2;
    private static final String FIND_KEYWORD = "find";

    /**
     * Returns true.
     * If user inputted a delete command.
     *
     * @param userInput The input given by user.
     * @return Returns true if user inputted a delete command.
     */
    public static boolean isDeleteCommand(String userInput) {
        return DELETE_KEYWORD.equals(userInput);
    }

    /**
     * Returns true.
     * If user inputted a set done command.
     *
     * @param userInput The input given by user.
     * @return Returns true if user inputted a set done command.
     */
    public static boolean isSetDoneCommand(String userInput) {
        return DONE_KEYWORD.equals(userInput);
    }

    /**
     * Returns true.
     * If user inputted an add Event command.
     *
     * @param userInput The input given by user.
     * @return Returns true if user inputted an add Event command.
     */
    public static boolean isAddEventCommand(String userInput) {
        return EVENT_KEYWORD.equals(userInput);
    }

    /**
     * Returns true.
     * If user inputted an add Deadline command.
     *
     * @param userInput The input given by user.
     * @return Returns true if user inputted an add Deadline command.
     */
    public static boolean isAddDeadlineCommand(String userInput) {
        return DEADLINE_KEYWORD.equals(userInput);
    }

    /**
     * Returns true.
     * If user inputted an add ToDo command.
     *
     * @param userInput The input given by user.
     * @return Returns true if user inputted an add ToDo command.
     */
    public static boolean isAddToDoCommand(String userInput) {
        return TO_DO_KEYWORD.equals(userInput);
    }

    /**
     * Returns true.
     * If user inputted a list command.
     *
     * @param userInput The input given by user.
     * @return Returns true if user inputted a list command.
     */
    public static boolean isListCommand(String userInput) {
        return userInput.replaceAll(" ", "").equals(LIST_KEYWORD);
    }

    /**
     * Returns true.
     * If user inputted an exit command.
     *
     * @param userInput The input given by user.
     * @return Returns true if user inputted an exit command.
     */
    public static boolean isExitCommand(String userInput) {
        return userInput.replaceAll(" ", "").equals(BYE_KEYWORD);
    }

    /**
     * Returns true.
     * If user inputted a find command.
     *
     * @param userInput The input given by user.
     * @return Returns true if user inputted a find command.
     */
    public static boolean isFindCommand(String userInput) {
        return userInput.replaceAll(" ", "").equals(FIND_KEYWORD);
    }

    /**
     * Returns a processed String that can be easily split into parts later.
     * This function is mainly used by DataManager to process each data entry.
     *
     * @param data String to be processed.
     * @return A processed String.
     */
    public static String processFileData(String data) {
        data = data.replace(']', ',');
        data = data.replace('[', ' ');
        return data;
    }

    /**
     * Returns an array of Strings each representing a characteristic of the Task class.
     * If String given cannot be split into 3 parts, String is considered corrupted. Exception will be thrown.
     * This function is mainly used by DataManager to process each data entry.
     *
     * @param data String to be split.
     * @return Split data.
     * @throws DukeMissingDataException If data cannot be split into 3 parts.
     */
    public static String[] splitToDataParts(String data) throws DukeMissingDataException {
        String[] dataParts = data.split(",");
        if (dataParts.length < TOTAL_DATA_PARTS) {
            throw new DukeMissingDataException();
        }
        for (int i = 0; i < TOTAL_DATA_PARTS; i++) {
            dataParts[i] = dataParts[i].trim();
        }
        return dataParts;
    }

    /**
     * Returns true.
     * If entry is a ToDo entry.
     * Done by checking the TASK_TYPE_INDEX of dataParts.
     * This function is mainly used by DataManager to process each data entry.
     *
     * @param dataParts Data obtained from DukeData.txt that is split into key parts.
     * @return True if entry is a ToDo entry.
     */
    public static boolean isTodoEntry(String[] dataParts) {
        return dataParts[TASK_TYPE_INDEX].equals("T");
    }

    /**
     * Returns true.
     * If entry is a Deadline entry.
     * Done by checking the TASK_TYPE_INDEX of dataParts.
     * This function is mainly used by DataManager to process each data entry.
     *
     * @param dataParts Data obtained from DukeData.txt that is split into key parts.
     * @return True if entry is a Deadline entry.
     */
    public static boolean isDeadlineEntry(String[] dataParts) {
        return dataParts[TASK_TYPE_INDEX].equals("D");
    }

    /**
     * Returns true.
     * If entry is an Event entry.
     * Done by checking the TASK_TYPE_INDEX of dataParts.
     * This function is mainly used by DataManager to process each data entry.
     *
     * @param dataParts Data obtained from DukeData.txt that is split into key parts.
     * @return True if entry is an Event entry.
     */
    public static boolean isDoneEntry(String[] dataParts) {
        return dataParts[DONE_INDEX].equals("X");
    }

    /**
     * Changes Deadline description field in dataParts.
     * This function is mainly used by DataManager to process each data entry.
     *
     * @param dataParts Contains Strings that needs to be processed.
     */
    public static void processDeadlineDescription(String[] dataParts) {
        dataParts[DESCRIPTION_INDEX] = dataParts[DESCRIPTION_INDEX].replace(")", "");
        dataParts[DESCRIPTION_INDEX] = dataParts[DESCRIPTION_INDEX].replace("(by:", "/by");
    }

    /**
     * Changes Event description field in dataParts.
     * This function is mainly used by DataManager to process each data entry.
     *
     * @param dataParts Contains Strings that needs to be processed.
     */
    public static void processEventDescription(String[] dataParts) {
        dataParts[DESCRIPTION_INDEX] = dataParts[DESCRIPTION_INDEX].replace(")", "");
        dataParts[DESCRIPTION_INDEX] = dataParts[DESCRIPTION_INDEX].replace("(at:", "/at");
    }

    /**
     * Returns task index given by user in int format so can use methods that take in int.
     *
     * @param word Index given by user in String format.
     * @return Task index given by user in int format.
     */
    public static int getTaskIndex(String word) {
        return Integer.parseInt(word.replaceAll(" ", ""));
    }

    /**
     * Returns an array of Strings where first String is user command.
     * So that methods that take in commands only can be used.
     *
     * @param line Full input given by user.
     * @return Returns an array of Strings where first String is user command.
     */
    public static String[] splitCommandAndRemainder(String line) {
        return line.split(" ", 2);
    }

    /**
     * Splits a sentence into individual words.
     *
     * @param sentence The sentence that will be split into individual words.
     * @return An array of Strings each String is a word in the sentence.
     */
    public static String[] splitIntoWords(String sentence) {
        return sentence.split(" ");
    }

    /**
     * Splits the full description given by user further into to description field and the by/at field.
     * Usually done on full description of Deadlines and Events.
     *
     * @param description The description of Deadline or Event.
     * @param splitBy     The String to split at.
     * @return An array of String of size 2. First String is the description. Second is the by/at field.
     */
    public static String[] splitDescription(String description, String splitBy) {
        return description.split(splitBy, 2);
    }

    /**
     * Returns true.
     * If the given String has only 1 word.
     * 
     * @param string The String that we are checking.
     * @return True if the given String only has 1 word.
     */
    public static boolean isOneWord(String string) {
        String[] words = string.split(" ");
        return words.length == 1;
    }
}
