package duke.parser;

import duke.extract.ExtractDeadlineDescription;
import duke.extract.ExtractDueTime;
import duke.extract.ExtractEventDescription;
import duke.extract.ExtractLocation;
import duke.exception.AtEmptyException;
import duke.exception.ByEmptyException;

public class Parser {

    private static final int NUMBER_OF_INFO_PARAM = 2;
    private static final int DUE_TIME_INDEX = 1;
    private static final int LOCATION_INDEX = 1;

    public static String splitTodo(String userInput) throws StringIndexOutOfBoundsException {
        String todoTask = userInput.replace("todo", "");
        todoTask = todoTask.trim();
        if (todoTask.equals("")) {
            throw new StringIndexOutOfBoundsException();
        }
        return todoTask;
    }

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

    public static String splitKeyword(String userInput) throws StringIndexOutOfBoundsException {
        String keyword = userInput.replace("find", "");
        keyword = keyword.trim();
        if (keyword.equals("")) {
            throw new StringIndexOutOfBoundsException();
        }
        return keyword;
    }
}
