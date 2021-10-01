package duke.parser;

import duke.extract.ExtractDeadlineDescription;
import duke.extract.ExtractDueTime;
import duke.extract.ExtractEventDescription;
import duke.extract.ExtractLocation;
import duke.exception.AtEmptyException;
import duke.exception.ByEmptyException;

/**
 * Splits the user input
 */
public class Parser {

    private static final int NUMBER_OF_INFO_PARAM = 2;
    private static final int DUE_TIME_INDEX = 1;
    private static final int LOCATION_INDEX = 1;

    /**
     * Returns only the description of the todo task
     * @param userInput input from the user
     * @return description of the todo task
     * @throws StringIndexOutOfBoundsException required field is left empty by the user
     */
    public static String splitTodo(String userInput) throws StringIndexOutOfBoundsException {
        String todoTask = userInput.replace("todo", "");
        todoTask = todoTask.trim();
        if (todoTask.equals("")) {
            throw new StringIndexOutOfBoundsException();
        }
        return todoTask;
    }

    /**
     * Returns an array with deadline information
     * @param userInput input from the user
     * @return String array that contains deadline description and deadline due time
     * @throws StringIndexOutOfBoundsException required field is left empty by the user
     * @throws ByEmptyException "by" field is left empty
     */
    public static String[] splitDeadline(String userInput) throws StringIndexOutOfBoundsException,
            ByEmptyException {
        String[] deadlineInfo = new String[NUMBER_OF_INFO_PARAM];
        int dividerPosition = userInput.indexOf("/by");
        ExtractDeadlineDescription.extract(userInput, deadlineInfo, dividerPosition);
        ExtractDueTime.extract(userInput, deadlineInfo, dividerPosition);
        if (deadlineInfo[DUE_TIME_INDEX].equals("")) {
            throw new ByEmptyException();
        }
        return deadlineInfo;
    }

    /**
     * Returns an array with event information
     * @param userInput input from the user
     * @return String array that contains event description and event location
     * @throws StringIndexOutOfBoundsException required field is left empty by the user
     * @throws AtEmptyException "at" field is left empty
     */
    public static String[] splitEvent(String userInput) throws StringIndexOutOfBoundsException,
            AtEmptyException {
        String[] eventInfo = new String[NUMBER_OF_INFO_PARAM];
        int dividerPosition = userInput.indexOf("/at");
        ExtractEventDescription.extract(userInput, eventInfo, dividerPosition);
        ExtractLocation.extract(userInput, eventInfo, dividerPosition);
        if (eventInfo[LOCATION_INDEX].equals("")) {
            throw new AtEmptyException();
        }
        return eventInfo;
    }

    /**
     * Returns keyword that the user is finding
     * @param userInput input from the user
     * @return keyword that the user is finding
     * @throws StringIndexOutOfBoundsException required field is left empty by the user
     */
    public static String splitKeyword(String userInput) throws StringIndexOutOfBoundsException {
        String keyword = userInput.replace("find", "");
        keyword = keyword.trim();
        if (keyword.equals("")) {
            throw new StringIndexOutOfBoundsException();
        }
        return keyword;
    }
}
