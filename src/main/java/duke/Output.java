package duke;

import duke.task.Task;
import java.util.ArrayList;

public class Output {
    private static final String SPACER = "  ";

    public static String getStartMessage() {
        String startMessage = SPACER + "Hello! I'm Duke" + System.lineSeparator()
                + SPACER + "How may I assist you" + System.lineSeparator();
        return startMessage;
    }

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

    public static String getExitMessage() {
        String exitMessage = SPACER + "Goodbye! Hope to see you soon!" + System.lineSeparator();
        return exitMessage;
    }

    public static String getAddTaskMessage(Task task, int tasksCount) {
        String addTaskMessage = SPACER + "Ok! I've added this task:" + System.lineSeparator()
                + SPACER + SPACER + task + System.lineSeparator()
                + SPACER + "Now you have " + tasksCount + " tasks." + System.lineSeparator();
        return addTaskMessage;
    }

    public static String getCompleteTaskMessage(Task task) {
        String completeTaskMessage = SPACER + "Ok! I've marked this task as done:" + System.lineSeparator()
                + SPACER + SPACER + task + System.lineSeparator();
        return completeTaskMessage;
    }

    public static String getDeleteTaskMessage(Task removedTask, int tasksCount) {
        String deleteTaskMessage = SPACER + "Ok! I've deleted this task:" + System.lineSeparator()
                + SPACER + SPACER + removedTask + System.lineSeparator()
                + SPACER + "Now you have " + tasksCount + " tasks." + System.lineSeparator();
        return deleteTaskMessage;
    }

    public static String getTaskListMessage(ArrayList<Task> tasks) {
        String taskListMessage = SPACER + "Here are your tasks:" + System.lineSeparator();
        for (int i = 0; i < tasks.size(); i++) {
            taskListMessage += SPACER + SPACER + (i + 1) + "." + tasks.get(i) + System.lineSeparator();
        }
        return taskListMessage;
    }
}
