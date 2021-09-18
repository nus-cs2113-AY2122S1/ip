package duke.message;

/**
 * <h1>Message</h1>
 * This class contains messages stored as final String variables.
 * It also contains methods for returning more dynamic messages which vary depending on the user input.
 */
public class Message {
    public static final String INVALID_INPUT_MESSAGE = "  Invalid Input..." + System.lineSeparator()
            + "  Please enter a valid input!";
    public static final String TASK_NUMBER_OUT_OF_RANGE_MESSAGE = "  Invalid Task number!" + System.lineSeparator()
            + "  Please try again with a Task number within range! :)";
    public static final String TASK_NUMBER_WRONG_FORMAT_MESSAGE = "  Invalid Task number format!"
            + System.lineSeparator() + "  Please enter a valid integer! :)";
    public static final String FAILED_TO_LOAD_LINE_MESSAGE = "  Failed to load a line from saved data...";
    public static final String EMPTY_TASK_LIST_MESSAGE = "  Task List is empty!";
    public static final String FAILED_TO_CREATE_DATA_FILE_MESSAGE = "  Failed to create new user data file!";
    public static final String SAVE_DATA_ERROR_MESSAGE = "  Something went wrong when writing into user data file!";
    public static final String DATA_FILE_NOT_FOUND = "  Data file not found!";

    /**
     * Returns message for user inputs that do not have task descriptions.
     *
     * @param taskType Type of the task as a String.
     * @return String Message depending on task type.
     */
    public static String getMessageForMissingTaskDescription(String taskType) {
        return "  I'm sorry... For a <" + taskType + "> you must include a description!"
                + System.lineSeparator() + "  Please try again with a valid input! :)";
    }

    /**
     * Returns message for successfully adding a task.
     *
     * @param taskDescriptionWithStatus The task description and its status as a String.
     * @param numberOfTasks Total number of tasks.
     * @return String Message for added task.
     */
    public static String getMessageForAddingTask(String taskDescriptionWithStatus, int numberOfTasks) {
        return "  You have successfully added the task:" + System.lineSeparator()
                + "    " + taskDescriptionWithStatus + System.lineSeparator()
                + "  You now have " + numberOfTasks + " tasks in your list!";
    }

    /**
     * Returns message for successfully deleting a task.
     *
     * @param taskDescriptionWithStatus The task description and its status as a String.
     * @param numberOfTasksRemaining Number of tasks remaining.
     * @return String Message for deleted task.
     */
    public static String getMessageForDeletingTask(String taskDescriptionWithStatus, int numberOfTasksRemaining) {
        return "  You have successfully deleted the task:" + System.lineSeparator()
                + "    " + taskDescriptionWithStatus + System.lineSeparator()
                + "  You now have " + numberOfTasksRemaining + " tasks in your list!";
    }

    /**
     * Returns message for successfully marking a task as done.
     *
     * @param taskDescription The task description and its status as a String.
     * @param taskNumber The task's number in the task list.
     * @return String Message for marking task as done.
     */
    public static String getMessageForMarkingTaskAsDone(String taskDescription, int taskNumber) {
        return "Task " + taskNumber + ": " + taskDescription
                + System.lineSeparator() + "  Marked as done!";
    }
}
