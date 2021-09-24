package duke;

import duke.task.Task;
import java.util.ArrayList;

public class Output {
    private static final String TAB = "  ";
    private static final String START_MESSAGE = TAB + "Hello! I'm Duke" + System.lineSeparator()
            + TAB + "How may I assist you" + System.lineSeparator();
    private static final String HELP_MESSAGE = TAB + "Here is a list of commands:" + System.lineSeparator()
            + TAB + TAB + "todo [task name] - adds todo task to task manager" + System.lineSeparator()
            + TAB + TAB + "event [task name] /at [date] - adds event task to task manager" + System.lineSeparator()
            + TAB + TAB + "deadline [task name] /by [date] - adds deadline task to task manager" + System.lineSeparator()
            + TAB + TAB + "list - lists all tasks" + System.lineSeparator()
            + TAB + TAB + "done [task index] - marks the specified task as completed" + System.lineSeparator()
            + TAB + TAB + "delete [task index] - deletes the specified task" + System.lineSeparator()
            + TAB + TAB + "find [keyword] - lists all tasks whose name contains the keyword" + System.lineSeparator()
            + TAB + TAB + "bye - close the application" + System.lineSeparator();
    private static final String EXIT_MESSAGE = TAB + "Goodbye! Hope to see you soon!" + System.lineSeparator();
    private static final String ADD_TASK_MESSAGE = TAB + "Ok! I've added this task:" + System.lineSeparator()
            + TAB + TAB + "%s" + System.lineSeparator()
            + TAB + "Now you have " + "%d" + " tasks." + System.lineSeparator();
    private static final String COMPLETE_TASK_MESSAGE = TAB + "Ok! I've marked this task as done:" + System.lineSeparator()
            + TAB + TAB + "%s" + System.lineSeparator();
    private static final String DELETE_TASK_MESSAGE = TAB + "Ok! I've deleted this task:" + System.lineSeparator()
            + TAB + TAB + "%s" + System.lineSeparator()
            + TAB + "Now you have " + "%d" + " tasks." + System.lineSeparator();
    private static final String LIST_TASK_MESSAGE_HEADER = TAB + "Here are your tasks:" + System.lineSeparator();
    private static final String LIST_TASK_MESSAGE_ENTRY = TAB + TAB + "%d" + "." + "%s" + System.lineSeparator();
    private static final String FIND_TASK_MESSAGE_HEADER = TAB + "Here are your tasks that contain the keyword \"" + "%s" + "\":" + System.lineSeparator();

    /**
     * Gets the Duke startup message.
     *
     * @return String containing Duke startup message
     */
    public static String getStartMessage() {
        return START_MESSAGE;
    }

    /**
     * Gets the help message containing a list of all Duke commands.
     *
     * @return String containing a list of all Duke commands
     */
    public static String getHelpMessage() {
        return HELP_MESSAGE;
    }

    /**
     * Gets the Duke exit message.
     *
     * @return String containing Duke exit message
     */
    public static String getExitMessage() {
        return EXIT_MESSAGE;
    }

    /**
     * Gets the message indicating that a task has been successfully added.
     *
     * @param task       Task that was added
     * @param tasksCount the new number of Tasks
     * @return String containing the message that a task was added successfully
     */
    public static String getAddTaskMessage(Task task, int tasksCount) {
        return String.format(ADD_TASK_MESSAGE, task, tasksCount);
    }

    /**
     * Gets the message indicating that a task was marked as completed.
     *
     * @param task Task that was marked as completed
     * @return String containing the message that a task was marked as completed
     */
    public static String getCompleteTaskMessage(Task task) {
        return String.format(COMPLETE_TASK_MESSAGE, task);
    }

    /**
     * Gets the message indicating that a task was successfully deleted.
     *
     * @param removedTask the Task that was deleted
     * @param tasksCount  the new number of Tasks
     * @return String containing the message that a task was deleted successfully
     */
    public static String getDeleteTaskMessage(Task removedTask, int tasksCount) {
        return String.format(DELETE_TASK_MESSAGE, removedTask, tasksCount);
    }

    /**
     * Gets a message listing out all the Tasks and their information.
     *
     * @param tasks ArrayList containing all the Tasks
     * @return String containing the message listing out all Tasks and their information
     */
    public static String getTaskListMessage(ArrayList<Task> tasks) {
        String taskListMessage = LIST_TASK_MESSAGE_HEADER;
        for (int i = 0; i < tasks.size(); i++) {
            taskListMessage += String.format(LIST_TASK_MESSAGE_ENTRY, i + 1, tasks.get(i));
        }
        return taskListMessage;
    }

    /**
     * Gets a message listing out all the Tasks whose names contain the keyword, together with their information.
     *
     * @param tasks   ArrayList containing the Tasks that matched the keyword
     * @param keyword String that was searched for in TaskList
     * @return String containing the message listing out all Tasks and their information
     */
    public static String getSearchedTaskListMessage(ArrayList<Task> tasks, String keyword) {
        String taskListMessage = String.format(FIND_TASK_MESSAGE_HEADER, keyword);
        for (int i = 0; i < tasks.size(); i++) {
            taskListMessage += String.format(LIST_TASK_MESSAGE_ENTRY, i + 1, tasks.get(i));
        }
        return taskListMessage;
    }


}
