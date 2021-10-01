package duke;

import duke.exception.*;
import duke.task.Task;

import java.util.ArrayList;


/**
 * The UI class contains the scripted replies of Duke to the user
 */
public class DukeUI {
    private static final int DEFAULT_LINE_LENGTH = 60;
    private static final String LINEBREAK = System.lineSeparator();
    private static final String LOGO = " ____        _" + LINEBREAK
            + "|  _ \\ _   _| | _____" + LINEBREAK
            + "| | | | | | | |/ / _ \\" + LINEBREAK
            + "| |_| | |_| |   <  __/" + LINEBREAK
            + "|____/ \\__,_|_|\\_\\___|" + LINEBREAK;

    /**
     * Greets the user when the program is initialized
     */
    public static void greet() {
        drawHorizontalLine();
        System.out.println("Hello! I'm Duke, your personal assistant.");
        System.out.println("How can I help you?");
        drawHorizontalLine();
    }

    /**
     * Says goodbye before terminate the program
     */
    public static void sayGoodbye() {
        drawHorizontalLine();
        System.out.println("Bye. Hope to see you again soon!");
        drawHorizontalLine();
    }

    /**
     * Draws a horizontal line to separate text command
     */
    public static void drawHorizontalLine() {
        for (int i = 0; i < DEFAULT_LINE_LENGTH; i++) {
            System.out.print("\u2500");
        }
        System.out.println();
    }

    /**
     * Prints out a list of task found using the 'list' or 'find' command
     *
     * @param tasks          The task list to be printed
     * @param isSearchedList determined if this is a list specify by 'list' or 'find' command
     */
    public static void printTaskList(ArrayList<Task> tasks, boolean isSearchedList) {
        drawHorizontalLine();
        String status;
        if (isSearchedList && tasks.size() == 0) {
            status = "Sorry, I can't found any related task :(((((";
        } else if (!isSearchedList && tasks.size() == 0) {
            status = "No task added yet!";
        } else if (!isSearchedList && tasks.size() != 0) {
            status = "Here is your list at the moment:";
        } else {
            status = "Here is the list of task with matching keywords";
        }
        System.out.println(status);
        for (int i = 0; i < tasks.size(); i++) {
            System.out.printf("%d. %s" + LINEBREAK, i + 1, tasks.get(i).toString());
        }
        drawHorizontalLine();
    }

    /**
     * Prints the announcement that the task has been added successfully
     *
     * @param task         the newly added task
     * @param taskListSize the size of the list after the addition of the new task
     */
    public static void printCompleteAddTask(Task task, int taskListSize) {
        drawHorizontalLine();
        System.out.println("Got it. I've added this task:");
        System.out.println(task);
        System.out.printf("Now you have %d tasks in the list" + LINEBREAK, taskListSize);
        drawHorizontalLine();
    }

    /**
     * Prints the announcement that the task has been marked done successfully
     *
     * @param task the completed task
     */
    public static void printMarkTaskDone(Task task) {
        drawHorizontalLine();
        System.out.printf("I have marked \"%s\" as done" + LINEBREAK, task.getDescription());
        drawHorizontalLine();
    }

    /**
     * Prints the announcement that the task has been deleted successfully
     *
     * @param task         The deleted task
     * @param taskListSize the size of the task list after the deletion
     */
    public static void printCompleteDeleteTask(Task task, int taskListSize) {
        drawHorizontalLine();
        System.out.println("Noted. I've removed this task:");
        System.out.println(task);
        System.out.printf("Now you have %d tasks in the list" + LINEBREAK, taskListSize);
        drawHorizontalLine();
    }

    /**
     * Prints the DUKE logo
     */
    public static void printLogo() {
        System.out.println(LOGO);
    }

    /**
     * Prints out the run-time error for the user
     *
     * @param e the given error
     */
    public static void printError(Exception e) {
        if (e instanceof WrongCommandException) {
            printWrongCommandError();
        } else if (e instanceof EmptyDescriptionException) {
            printEmptyDescriptionError();
        } else if (e instanceof MissingParameterException) {
            printMissingParameterError();
        } else if (e instanceof TaskNotFoundException) {
            printTaskNotFoundError();
        } else if (e instanceof DukeDateTimeFormatException) {
            printDateTimeFormatError();
        } else {
            drawHorizontalLine();
            System.out.println("☹ OOPS!!! Something wrong with the system");
            drawHorizontalLine();
        }
    }

    /**
     * Prints error message indicating the input command is not supported
     */
    private static void printWrongCommandError() {
        drawHorizontalLine();
        System.out.println("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
        drawHorizontalLine();
    }

    /**
     * Prints error message indicating a missing parameter in the input task
     */
    private static void printMissingParameterError() {
        drawHorizontalLine();
        System.out.println("☹ OOPS!!! Your command is missing some variables.");
        drawHorizontalLine();
    }

    /**
     * Prints error message indicating an empty description for a task
     */
    private static void printEmptyDescriptionError() {
        drawHorizontalLine();
        System.out.println("☹ OOPS!!! The description of your command cannot be empty.");
        drawHorizontalLine();
    }

    /**
     * Prints error message indicating the required task cannot be found
     */
    private static void printTaskNotFoundError() {
        drawHorizontalLine();
        System.out.println("☹ OOPS!!! The task you are looking for can't be found :-(");
        drawHorizontalLine();
    }

    /**
     * Prints error message indicating the input date and time is in the wrong format
     */
    private static void printDateTimeFormatError() {
        drawHorizontalLine();
        System.out.println("☹ OOPS!!! Please try to input the date time in the following form: yyyy-MM-dd HH:mm");
        drawHorizontalLine();
    }
}
