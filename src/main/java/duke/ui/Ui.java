package duke.ui;

import duke.task.Task;
import duke.tasklist.TaskList;

import java.util.ArrayList;

/** Represents user interfaces between duke and user */
public class Ui {
    protected final String DIVIDER = "--------------------------------------------";

    /** Prints the welcome message at start of program. */
    public void printWelcomeMessage() {
        System.out.println("Hello I'm Duke\nWhat can I do for you?");
        System.out.println(DIVIDER);
    }

    /** Prints the exit message at end of program. */
    public void printExitMessage() {
        System.out.println("Bye! Have a nice day.");
        System.out.println(DIVIDER);
    }

    /** Prints the divider */
    public void printDivider() {
        System.out.println(DIVIDER);
    }

    /** Prints error message to inform user about invalid input. */
    public void printInvalidCommand() {
        System.out.println("User command is not valid, please try again!");
    }

    /**
     * Prints message to inform user task is added successfully.
     *
     * @param taskList list of tasks
     */
    public static void printAddSuccess(TaskList taskList) {
        int lastTaskIndex = taskList.getTasks().size() - 1;
        System.out.println("Got it. I've added this task:\n  " + taskList.getTasks().get(lastTaskIndex));
        System.out.println("Now you have " + taskList.getTasks().size() + " tasks in the list");
    }

    /** Prints message to inform user no matching task with given keyword */
    public static void printNoMatchTask() {
        System.out.println("No tasks found with given keyword. Please try again!");
    }

    /**
     * Prints the list of task specified
     *
     * @param tasks tasks to print
     */
    public static void printTasks(ArrayList<Task> tasks) {
        int currentIndex = 1;
        for (Task task : tasks) {
            System.out.println(currentIndex + ". " + task.toString());
            currentIndex++;
        }
    }

}
