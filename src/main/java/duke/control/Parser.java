package duke.control;

public class Parser {
    private static final int TODO_NAME_START_INDEX = 5;
    private static final int DEADLINE_NAME_START_INDEX = 9;
    private static final int EVENT_NAME_START_INDEX = 6;
    private static final int DATETIME_START_INDEX_OFFSET = 4;
    private static final int FILE_TASK_NAME_INDEX = 7;
    private static final int FILE_TASKTYPE_INDEX = 1;
    private static final int FILE_ISDONE_INDEX = 4;

    protected static String parseInputForDateTime(String input) {
        int markerIndex = input.indexOf('/');
        int dateTimeStartIndex = markerIndex + DATETIME_START_INDEX_OFFSET;
        return (input.substring(dateTimeStartIndex).trim());
    }

    protected static TaskList.TaskType parseTaskType(String input) throws InvalidInputFormatException {
        if (input.startsWith("deadline") && input.contains(" /by")) {
            return TaskList.TaskType.DEADLINE;
        }
        if (input.startsWith("event") && input.contains(" /at")) {
            return TaskList.TaskType.EVENT;
        }
        if (input.startsWith("todo")) {
            return TaskList.TaskType.TODO;
        }
        throw new InvalidInputFormatException();
    }

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

    protected static String parseDateTimeFromFile(String inputLineFromFile) throws InvalidInputFormatException {
        int markerIndex = inputLineFromFile.indexOf('(');
        int endIndex = inputLineFromFile.indexOf(')');
        int dateTimeStartIndex = markerIndex + DATETIME_START_INDEX_OFFSET;
        return (inputLineFromFile.substring(dateTimeStartIndex, endIndex).trim());
    }

    protected static String parseDescriptionFromFile(String inputLineFromFile, TaskList.TaskType taskType) throws
            InvalidInputFormatException, StringIndexOutOfBoundsException {
        if (taskType.equals(TaskList.TaskType.TODO)) {
            return inputLineFromFile.substring(FILE_TASK_NAME_INDEX);
        } else if (taskType.equals(TaskList.TaskType.DEADLINE) || taskType.equals(TaskList.TaskType.EVENT)) {
            return inputLineFromFile.substring(FILE_TASK_NAME_INDEX, inputLineFromFile.indexOf(" ("));
        } else {
            throw new InvalidInputFormatException();
        }
    }
}
