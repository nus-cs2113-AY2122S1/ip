package duke.control;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

/**
 * Takes input for adding Tasks and parses the information. Input is a String, either from user or from save file.
 */
public class Parser {
    private static final int FILE_TASKTYPE_INDEX = 1;
    private static final int FILE_DATETIME_INDEX_OFFSET = 3;
    private static final int DATETIME_START_INDEX_OFFSET = 4;
    private static final int FILE_ISDONE_INDEX = 4;
    private static final int TODO_NAME_START_INDEX = 5;
    private static final int EVENT_NAME_START_INDEX = 6;
    private static final int FILE_TASK_NAME_INDEX = 7;
    private static final int DEADLINE_NAME_START_INDEX = 9;

    /**
     * Parses the user input to obtain Date and Time information for Deadline and Event tasks
     * Method is called by parseTaskType, which performs the check for whether the input is in the correct format,
     * so it can be assumed that the input is valid. Format is yyyy-mm-dd xx:xx
     *
     * @param input user input String
     * @return the Date and Time information for a Deadline or Event task
     */
    protected static LocalDateTime parseInputForDateTime(String input) throws DateTimeParseException {
        int markerIndex = input.indexOf('/');
        int dateTimeStartIndex = markerIndex + DATETIME_START_INDEX_OFFSET;
        String dateTimeString = input.substring(dateTimeStartIndex).trim();
        LocalDateTime dateTime = LocalDateTime.parse(dateTimeString);
        return dateTime;
    }

    /**
     * Parses task type from user input for adding a new task to the list.
     * Task type is either Todo, Deadline or Event.
     * @param input line of input from user
     * @return TaskType of the input
     * @throws InvalidInputFormatException
     */
    protected static TaskList.TaskType parseTaskType(String input) throws InvalidInputFormatException {
        if (input.startsWith("deadline") && input.contains(" /by ")) {
            return TaskList.TaskType.DEADLINE;
        }
        if (input.startsWith("event") && input.contains(" /at ")) {
            return TaskList.TaskType.EVENT;
        }
        if (input.startsWith("todo")) {
            return TaskList.TaskType.TODO;
        }
        throw new InvalidInputFormatException();
    }

    /**
     * Parses task description from user input for adding a new task to the list.
     * @param input Line of input from user
     * @param taskType type of task, Todo, Deadline or event, which affects the start index of the description.
     * @return Description of the task to be added to the list.
     */
    protected static String parseDescription(String input, TaskList.TaskType taskType) {
        switch (taskType) {
        case TODO:
            return input.substring(TODO_NAME_START_INDEX);
        case DEADLINE:
            return input.substring(DEADLINE_NAME_START_INDEX, input.indexOf(" /"));
        case EVENT:
            return input.substring(EVENT_NAME_START_INDEX, input.indexOf(" /"));
        default:
            return input;
        }
    }

    /**
     * Parses the isDone status from a line of input from save file.
     * @param inputLineFromFile line of input from save file
     * @return isDone, whether the task in the input line is done.
     * @throws InvalidInputFormatException
     */
    protected static boolean parseIsDoneFromFile(String inputLineFromFile) throws InvalidInputFormatException {
        switch (inputLineFromFile.charAt(FILE_ISDONE_INDEX)) {
        case (' '):
            return false;
        case ('X'):
            return true;
        default:
            throw new InvalidInputFormatException();
        }
    }

    /**
     * Parses the task type from a line of input from save file.
     * Task type is either Todo, Deadline or Event
     * @param inputLineFromFile line of input from save file
     * @return the TaskType of the task from the input line
     * @throws InvalidInputFormatException
     */
    protected static TaskList.TaskType parseTaskTypeFromFile(String inputLineFromFile) throws
            InvalidInputFormatException {
        switch (inputLineFromFile.charAt(FILE_TASKTYPE_INDEX)) {
        case ('T'):
            return TaskList.TaskType.TODO;
        case ('D'):
            return TaskList.TaskType.DEADLINE;
        case ('E'):
            return TaskList.TaskType.EVENT;
        default:
            throw new InvalidInputFormatException();
        }
    }

    /**
     * Parses date and time information from a line from the save file.
     * Data is saved in the form [D][X] name (by: MMM d yyyy
     *
     * @param inputLineFromFile
     * @return the date and time of the task object from the input line, in the form of a LocalDateTime object.
     * @throws InvalidInputFormatException
     */
    protected static LocalDateTime parseDateTimeFromFile(String inputLineFromFile) throws InvalidInputFormatException {
        int markerIndex = inputLineFromFile.indexOf("DT: ");
        int dateTimeStartIndex = markerIndex + FILE_DATETIME_INDEX_OFFSET;
        String dateTimeString = inputLineFromFile.substring(dateTimeStartIndex).trim();
        return LocalDateTime.parse(dateTimeString);
    }

    /**
     * Parses task description from a line from the save file
     *
     * @param inputLineFromFile
     * @param taskType
     * @return
     * @throws InvalidInputFormatException
     * @throws StringIndexOutOfBoundsException
     */
    protected static String parseDescriptionFromFile(String inputLineFromFile, TaskList.TaskType taskType) throws
            InvalidInputFormatException, StringIndexOutOfBoundsException {
        if (taskType.equals(TaskList.TaskType.TODO)) {
            return inputLineFromFile.substring(FILE_TASK_NAME_INDEX);
        } else if (taskType.equals(TaskList.TaskType.DEADLINE) || taskType.equals(TaskList.TaskType.EVENT)) {
            return inputLineFromFile.substring(FILE_TASK_NAME_INDEX, inputLineFromFile.indexOf("DT:"));
        } else {
            throw new InvalidInputFormatException();
        }
    }
}
