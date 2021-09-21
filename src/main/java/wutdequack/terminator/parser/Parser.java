package wutdequack.terminator.parser;

import static wutdequack.terminator.common.MagicValues.AT_KEYWORD;
import static wutdequack.terminator.common.MagicValues.BY_KEYWORD;
import static wutdequack.terminator.common.MagicValues.COMPLETION_INDEX;
import static wutdequack.terminator.common.MagicValues.DATE_TIME_INDEX;
import static wutdequack.terminator.common.MagicValues.DEADLINE_KEYWORD;
import static wutdequack.terminator.common.MagicValues.DEADLINE_TYPE;
import static wutdequack.terminator.common.MagicValues.DELIMINATOR_FOR_FILE;
import static wutdequack.terminator.common.MagicValues.EVENT_KEYWORD;
import static wutdequack.terminator.common.MagicValues.EVENT_TYPE;
import static wutdequack.terminator.common.MagicValues.KEYWORD_INDEX;
import static wutdequack.terminator.common.MagicValues.SEARCH_TERM_INDEX;
import static wutdequack.terminator.common.MagicValues.TASK_NAME_INDEX;
import static wutdequack.terminator.common.MagicValues.TASK_NUMBER_INDEX;
import static wutdequack.terminator.common.MagicValues.TODO_KEYWORD;
import static wutdequack.terminator.common.MagicValues.ui;

import java.util.Arrays;
import java.util.Objects;
import java.util.regex.Pattern;
import wutdequack.terminator.objects.exception.InsufficientParametersException;

public class Parser {


    public Parser() {
    }

    /**
     * Given user command to mark task as completed, return the task number.
     * @param userInput String containing the TaskNumber.
     * @return The TaskNumber to be marked as done
     */
    public int getTaskNumberFromInput(String userInput) throws IndexOutOfBoundsException{
        try {
            int taskNumber = Integer.parseInt(userInput.split(" ")[TASK_NUMBER_INDEX]) - 1;
            // If less than 0, throw exception
            if (taskNumber < 0) {
                throw new NumberFormatException();
            }
            return taskNumber;
        } catch (NumberFormatException e) {
            // todo
            ui.printInvalidUserNumberMessage();
            return -1;
        }
    }

    /**
     * Parse the String into different parts based on format.
     * @param fileFormattedString A string extracted from the input file.
     * @param eventType The type of task to extract based on.
     * @return String array of parsed Strings
     */
    public String[] parseFileFormattedString(String fileFormattedString, String eventType) {
        String[] returnArray = new String[3];
        String[] splitResult = fileFormattedString.split(Pattern.quote(DELIMINATOR_FOR_FILE));
        returnArray[COMPLETION_INDEX] = splitResult[1];
        returnArray[TASK_NAME_INDEX] = splitResult[2];
        if (Objects.equals(eventType, DEADLINE_TYPE) || Objects.equals(eventType, EVENT_TYPE)) {
            returnArray[DATE_TIME_INDEX] = splitResult[3];
        }
        return returnArray;
    }

    /**
     * Utilizes user input to extract Task Name and UserInput.
     * @param userInput The string given by the user.
     * @param eventType The type of task to extract based on.
     * @return String array containing userinput and eventType.
     */
    public String[] extractNameDateTime(String userInput, String eventType) {
        String[] returnArray = new String[3];
        if (Objects.equals(eventType, DEADLINE_TYPE)) {
            // Get indexes to substring
            int startOfByIndex = userInput.indexOf(BY_KEYWORD);
            int endOfByIndex = startOfByIndex + 3;
            int endOfDeadlineStringIndex = userInput.indexOf(DEADLINE_KEYWORD) + 8;

            // Get specific task_name and date_time
            String taskName = userInput.substring(endOfDeadlineStringIndex, startOfByIndex).strip();
            String dateTime = userInput.substring(endOfByIndex).strip();

            // Place values into array
            returnArray[TASK_NAME_INDEX] = taskName;
            returnArray[DATE_TIME_INDEX] = dateTime;
        } else if (Objects.equals(eventType, EVENT_TYPE)) {
            // Get indexes to substring
            int startOfAtIndex = userInput.indexOf(AT_KEYWORD);
            int endOfAtIndex = startOfAtIndex + 3;
            int endOfEventStringIndex = userInput.indexOf(EVENT_KEYWORD) + 5;

            // Get specific task_name and date_time
            String taskName = userInput.substring(endOfEventStringIndex, startOfAtIndex).strip();
            String dateTime = userInput.substring(endOfAtIndex).strip();

            // Place values into array
            returnArray[TASK_NAME_INDEX] = taskName;
            returnArray[DATE_TIME_INDEX] = dateTime;
        } else {
            // Extract values for ToDo
            int endOfToDoStringIndex = userInput.indexOf(TODO_KEYWORD) + 4;
            String taskName = userInput.substring(endOfToDoStringIndex).strip();
            returnArray[TASK_NAME_INDEX] = taskName;
        }
        return returnArray;
    }

    /**
     * Checks if the correct format is given based on the task option.
     * @param rawUserInput String given by the user.
     * @param taskType The type of task to determine the subclass to create.
     */
    public Boolean isCorrectFormat(String rawUserInput, String taskType) {
        try {
            boolean incorrectDeadlineString = taskType.equals(DEADLINE_TYPE) && !rawUserInput.contains(BY_KEYWORD);
            boolean incorrectEventString = taskType.equals(EVENT_TYPE) && !rawUserInput.contains(AT_KEYWORD);
            if (incorrectEventString || incorrectDeadlineString) {
                throw new InsufficientParametersException();
            }
        } catch (InsufficientParametersException e) {
            // If not enough parameters, print message and return False
            ui.printMissingParametersMessage();
            return false;
        }
        // If pass all checks, it is in the correct format
        return true;
    }

    /**
     * Extracts keyword from user input.
     * @param userLine Line that is inputted by the user.
     * @return String containing the keyword.
     */
    public String getKeywordFromUserInput(String userLine) {
        return userLine.split(" ")[KEYWORD_INDEX];
    }

    /**
     * Extracts search term from input
     * @param userLine Line that is inputted by the user.
     * @return String containing the search term
     */
    public String getSearchTermFromInput(String userLine) {
        String[] tokenizedInput = userLine.split(" ");
        String[] tokenizedSearchTerms = Arrays.copyOfRange(tokenizedInput, 1, tokenizedInput.length);
        return String.join(" ", tokenizedSearchTerms);
    }

}
