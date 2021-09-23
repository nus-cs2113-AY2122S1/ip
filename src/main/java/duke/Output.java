package duke;

import duke.task.Task;
import java.util.ArrayList;

public class Output {
    private static final String SPACER = "  ";

    /**
     * Gets the Duke startup message.
     *
     * @return String containing Duke startup message
     */
    public static String getStartMessage() {
        String startMessage = SPACER + "Hello! I'm Duke" + System.lineSeparator()
                + SPACER + "How may I assist you" + System.lineSeparator();
        return startMessage;
    }

    /**
     * Gets the help message containing a list of all Duke commands.
     *
     * @return String containing a list of all Duke commands
     */
    public static String getHelpMessage() {
        String helpMessage = "Here is a list of commands:" + System.lineSeparator()
                + SPACER + SPACER + "todo [task name] - adds todo task to task manager" + System.lineSeparator()
                + SPACER + SPACER + "event [task name] /at [date] - adds event task to task manager" + System.lineSeparator()
                + SPACER + SPACER + "deadline [task name] /by [date] - adds deadline task to task manager" + System.lineSeparator()
                + SPACER + SPACER + "list - lists all tasks" + System.lineSeparator()
                + SPACER + SPACER + "done [task index] - marks the specified task as completed" + System.lineSeparator()
                + SPACER + SPACER + "delete [task index] - deletes the specified task" + System.lineSeparator()
                + SPACER + SPACER + "bye - close the application" + System.lineSeparator();
        return helpMessage;
    }

    /**
     * Gets the Duke exit message.
     *
     * @return String containing Duke exit message
     */
    public static String getExitMessage() {
        String exitMessage = SPACER + "Goodbye! Hope to see you soon!" + System.lineSeparator();
        return exitMessage;
    }

    /**
     * Gets the message indicating that a task has been successfully added.
     *
     * @param task       Task that was added
     * @param tasksCount the new number of Tasks
     * @return String containing the message that a task was added successfully
     */
    public static String getAddTaskMessage(Task task, int tasksCount) {
        String addTaskMessage = SPACER + "Ok! I've added this task:" + System.lineSeparator()
                + SPACER + SPACER + task + System.lineSeparator()
                + SPACER + "Now you have " + tasksCount + " tasks." + System.lineSeparator();
        return addTaskMessage;
    }

    /**
     * Gets the message indicating that a task was marked as completed.
     *
     * @param task Task that was marked as completed
     * @return String containing the message that a task was marked as completed
     */
    public static String getCompleteTaskMessage(Task task) {
        String completeTaskMessage = SPACER + "Ok! I've marked this task as done:" + System.lineSeparator()
                + SPACER + SPACER + task + System.lineSeparator();
        return completeTaskMessage;
    }

    /**
     * Gets the message indicating that a task was successfully deleted.
     *
     * @param removedTask the Task that was deleted
     * @param tasksCount  the new number of Tasks
     * @return String containing the message that a task was deleted successfully
     */
    public static String getDeleteTaskMessage(Task removedTask, int tasksCount) {
        String deleteTaskMessage = SPACER + "Ok! I've deleted this task:" + System.lineSeparator()
                + SPACER + SPACER + removedTask + System.lineSeparator()
                + SPACER + "Now you have " + tasksCount + " tasks." + System.lineSeparator();
        return deleteTaskMessage;
    }


    /**
     * Gets a message listing out all the Tasks whose names contain the keyword, together with their information.
     *
     * @param tasks   ArrayList containing the Tasks that matched the keyword
     * @param keyword String that was searched for in TaskList
     * @return String containing the message listing out all Tasks and their information
     */
    public static String getSearchedTaskListMessage(ArrayList<Task> tasks, String keyword) {
        String taskListMessage = SPACER + "Here are your tasks that contain the keyword \"" + keyword + "\":" + System.lineSeparator();
        for (int i = 0; i < tasks.size(); i++) {
            taskListMessage += SPACER + SPACER + (i + 1) + "." + tasks.get(i) + System.lineSeparator();
        }
        return taskListMessage;
    }


    /**
     * Gets a message listing out all the Tasks and their information.
     *
     * @param tasks ArrayList containing all the Tasks
     * @return String containing the message listing out all Tasks and their information
     */
    public static String getTaskListMessage(ArrayList<Task> tasks) {
        String taskListMessage = SPACER + "Here are your tasks:" + System.lineSeparator();
        for (int i = 0; i < tasks.size(); i++) {
            taskListMessage += SPACER + SPACER + (i + 1) + "." + tasks.get(i) + System.lineSeparator();
        }
        return taskListMessage;
    }
}
