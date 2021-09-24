package duke.parser;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDo;
import duke.ui.HalUi;
import duke.util.HalException;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents the class which handles all text parsing functions in the programme
 */
public class Parser {
    private static int DEADLINE_INDEX = 9;
    private static int EVENT_INDEX = 5;
    private static int TODO_INDEX = 4;
    private static int TASK_STRING_OFFSET = 3;

    String description;
    String timing;

    HalUi ui = new HalUi();

    /**
     * Returns a list of String objects based on the text input.
     * If the format of the input text does not follow requirements, exception is thrown.
     *
     * @param task Task the type of task corresponding to the input.
     * @param input Input a string based on the text input by the user through CLI.
     * @return a parsed list of string literals containing the description and timing components (if relevant).
     * @throws HalException if format of input text is wrong.
     */
    public List<String> parseTextInput(Task task, String input) throws HalException {
        if (task instanceof Deadline && !input.contains("/by")) {
            ui.printErrorMessage();
            throw new HalException("Wrong Deadline task format");
        } else if (task instanceof Event && !input.contains("/at")) {
            ui.printErrorMessage();
            throw new HalException("Wrong Event task format");
        }


        try {
            if (task instanceof ToDo) {
                description = input.substring(TODO_INDEX).trim();
            } else if (task instanceof Event) {
                description = input.substring(EVENT_INDEX, input.indexOf('/')).trim();
                timing = input.substring(input.indexOf("/at") + TASK_STRING_OFFSET).trim();
            } else if (task instanceof Deadline) {
                description = input.substring(DEADLINE_INDEX, input.indexOf('/')).trim();
                timing = input.substring(input.indexOf("/by") + TASK_STRING_OFFSET).trim();
            }
        } catch (StringIndexOutOfBoundsException e) {
            ui.printErrorMessage();
            throw new HalException("Wrong task format");
        }

        //Error handling
        if (description.equals("")) {
            ui.printEmptyDescriptionMessage();
            throw new HalException("Empty description");
        } else if ((task instanceof Deadline || task instanceof Event) && timing.equals("")) {
            ui.printEmptyDateMessage();
            throw new HalException("Empty date");
        }

        List<String> returnVal = new ArrayList<>();
        returnVal.add(description);
        returnVal.add(timing);
        return returnVal;
    }

    /**
     * Returns an integer extracted from a string.
     * If an integer cannot be parsed from the string, a NumberFormatException is thrown.
     *
     * @param task Task the input string containing an integer value
     * @return the integer representing the integer of the task
     */
    public int parseInt(String task) {
        try {
            int taskNum = Integer.parseInt(task.substring(task.indexOf(' ') + 1));
            return taskNum;
        } catch (NumberFormatException error) {
            ui.printInvalidNumberMessage();
            ui.printEnterCommandMessage();
        }
        return -1;
    }

}
