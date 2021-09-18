package duke.message;

public class Message {
    public static final String INVALID_INPUT_MESSAGE = "  Invalid Input..." + System.lineSeparator()
            + "  Please enter a valid input!";
    public static final String TASK_NUMBER_OUT_OF_RANGE_MESSAGE = "  Invalid Task number!" + System.lineSeparator()
            + "  Please try again with a Task number within range! :)";
    public static final String TASK_NUMBER_WRONG_FORMAT_MESSAGE = "  Invalid Task number format!"
            + System.lineSeparator() + "  Please enter a valid integer! :)";
    public static final String FAILED_TO_LOAD_LINE_MESSAGE = "Failed to a line from saved data...";
    public static final String EMPTY_TASK_LIST_MESSAGE = "  Task List is empty!";
    public static final String FAILED_TO_CREATE_DATA_FILE_MESSAGE = "  Failed to create new user data file!";
    public static final String SAVE_DATA_ERROR_MESSAGE = "  Something went wrong when writing into user data file!";
    public static final String DATA_FILE_NOT_FOUND_MESSAGE = "  Data file not found!";
    public static final String KEYWORD_NOT_FOUND_MESSAGE = "  I'm sorry, I could not find any tasks with that keyword";
    public static final String NOT_DATE_TIME_MESSAGE = "  User input was not in DateTime Format: dd/MM/yyyy HH:mm"
            + System.lineSeparator() + "  Input will be stored as String...";

    public static String getMessageForMissingTaskDescription(String taskType) {
        return "  I'm sorry... For a <" + taskType + "> you must include a description!"
                + System.lineSeparator() + "  Please try again with a valid input! :)";
    }

    public static String getMessageForAddingTask(String taskDescriptionWithStatus, int numberOfTasks) {
        return "  You have successfully added the task:" + System.lineSeparator()
                + "    " + taskDescriptionWithStatus + System.lineSeparator()
                + "  You now have " + numberOfTasks + " tasks in your list!";
    }

    public static String getMessageForDeletingTask(String taskDescriptionWithStatus, int numberOfTasksRemaining) {
        return "  You have successfully deleted the task:" + System.lineSeparator()
                + "    " + taskDescriptionWithStatus + System.lineSeparator()
                + "  You now have " + numberOfTasksRemaining + " tasks in your list!";
    }

    public static String getMessageForMarkingTaskAsDone(String taskDescription, int taskNumber) {
        return "Task " + taskNumber + ": " + taskDescription
                + System.lineSeparator() + "  Marked as done!";
    }
}
