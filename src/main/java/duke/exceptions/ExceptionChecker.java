package duke.exceptions;

import duke.task.TaskManager;
import duke.util.InputParser;

public class ExceptionChecker {

    private static final InputParser PARSER = new InputParser();

    private static final String ECHO_ERROR =
            "OH NO! I can't echo if you don't say anything...";
    private static final String TODO_ERROR =
            "OH NO! You need to provide a description for your todo...";
    private static final String DEADLINE_DESCRIPTION_ERROR =
            "OH NO! You need to provide a description for your deadline task...";
    private static final String DEADLINE_DATE_ERROR =
            "OH NO! You need to specify a due date for your deadline task...";
    private static final String EVENT_DESCRIPTION_ERROR =
            "OH NO! You need to provide a description for your event...";
    private static final String EVENT_DATE_ERROR =
            "OH NO! You need to specify a date and time for your event...";
    private static final String INVALID_COMMAND_ERROR =
            "Sorry... I did not understand that, can you try again?\n"
                    + "Or you can enter \"help\" to see what I can do for you!";
    private static final String DONE_MISSING_NUMBER_ERROR =
            "OH NO! You need to specify the task you want to mark as done...\n"
                    + "Enter \"list\" to check the task number!";
    private static final String DONE_NUMBER_FORMAT_ERROR = "OH NO! That wasn't a number...";
    private static final String DONE_NUMBER_NOT_FOUND_ERROR =
            "OH NO! The task number is invalid, I can't find any tasks matching that number...\n"
                    + "Enter \"list\" to check the task number!";

    private boolean hasNullParameter(String[] inputArray) {
        return (inputArray.length < 2);
    }

    public String retrieveEchoParameter(String[] inputArray) throws DukeException {
        if (hasNullParameter(inputArray)) {
            throw new DukeException(ECHO_ERROR);
        }
        return inputArray[1];
    }

    public int retrieveDoneParameter(String[] inputArray) throws DukeException {
        int taskNumber;

        if (hasNullParameter(inputArray)) {
            throw new DukeException(DONE_MISSING_NUMBER_ERROR);
        }
        try {
            taskNumber = Integer.parseInt(inputArray[1]);
        } catch (NumberFormatException exception) {
            throw new DukeException(DONE_NUMBER_FORMAT_ERROR);
        }
        if (!TaskManager.isValidTaskNumber(taskNumber)) {
            throw new DukeException(DONE_NUMBER_NOT_FOUND_ERROR);
        }

        return taskNumber;
    }

    public String retrieveTodoParameter(String[] inputArray) throws DukeException {
        if (hasNullParameter(inputArray)) {
            throw new DukeException(TODO_ERROR);
        }
        return inputArray[1];
    }

    public String[] retrieveDeadlineParameters(String[] inputArray) throws DukeException {
        String[] params;

        if (hasNullParameter(inputArray)) {
            throw new DukeException(DEADLINE_DESCRIPTION_ERROR);
        }
        params = PARSER.separateDeadline(inputArray[1]);
        if (hasNullParameter(params)) {
            throw new DukeException(DEADLINE_DATE_ERROR);
        }
        if (params[0].isEmpty()) {
            throw new DukeException(DEADLINE_DESCRIPTION_ERROR);
        }
        if (params[1].isEmpty()) {
            throw new DukeException(DEADLINE_DATE_ERROR);
        }

        return params;
    }

    public String[] retrieveEventParameters(String[] inputArray) throws DukeException {
        String[] params;

        if (hasNullParameter(inputArray)) {
            throw new DukeException(EVENT_DESCRIPTION_ERROR);
        }
        params = PARSER.separateEvent(inputArray[1]);
        if (hasNullParameter(params)) {
            throw new DukeException(EVENT_DATE_ERROR);
        }
        if (params[0].isEmpty()) {
            throw new DukeException(EVENT_DESCRIPTION_ERROR);
        }
        if (params[1].isEmpty()) {
            throw new DukeException(EVENT_DATE_ERROR);
        }

        return params;
    }

    public void throwInput() throws DukeException {
        throw new DukeException(INVALID_COMMAND_ERROR);
    }
}
