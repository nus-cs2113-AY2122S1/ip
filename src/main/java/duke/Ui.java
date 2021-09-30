package duke;

import duke.data.TaskList;
import duke.task.Task;

import java.util.ArrayList;

/**
 * This class contains all the messages Duke would output to the user and acts as the ui
 */
public class Ui {
    private static final String DOTTED_LINE = "______________________________________________________________________________\n";

    public static void printWelcomeMessage() {
        printDivider();
        System.out.println("Hello! I'm Duke\n");
        System.out.println("What can I do for you?");
        printDivider();
    }

    public static void printExitMessage() {
        printDivider();
        System.out.println("Bye. Hope to see you again soon!");
        printDivider();
    }

    public static void printDivider() {
        System.out.println(DOTTED_LINE);
    }

    public static void printHelpMessage() {
        printDivider();
        System.out.println("Hi! If you need help on how to use me you've come to the right place!");
        System.out.println("Here's the list of commands I can handle now:");
        System.out.println("1.List - list");
        System.out.println("2.Add todo - todo <description>");
        System.out.println("3.Add deadline - deadline <description> /<due>");
        System.out.println("4.Add event - event <description> /<due>");
        System.out.println("5.Set task as done - done <task index>");
        System.out.println("5.Delete task - delete <task index>");
        System.out.println("6.Find tasks - find <search keywords>");
        printDivider();
    }

    public static void printInvalidTaskMessage() {
        printDivider();
        System.out.println("No such task exists");
        printDivider();
    }

    public static void printInvalidInputMessage() {
        printDivider();
        System.out.println("Invalid input for the given command");
        System.out.println("For information on how to use me try using the help command!");
        printDivider();
    }

    public static void printAddTaskMessage() {
        ArrayList<Task> taskList = TaskList.getTaskList();
        int taskCount = taskList.size();
        printDivider();
        System.out.println("Got it. I've added this task: ");
        System.out.println(taskList.get(taskCount - 1));
        System.out.println("You now have " + taskCount + " items in the list.");
        printDivider();
    }

    public static void printParameterErrorMessage() {
        printDivider();
        System.out.println("Missing parameters");
        System.out.println("For information on how to use me try using the help command!");
        printDivider();
    }

    public static void printInvalidCommandMessage() {
        printDivider();
        System.out.println("Invalid command entered. Please try again");
        System.out.println("For information on how to use me try using the help command!");
        printDivider();
    }

    public static void printEmptyListMessage() {
        printDivider();
        System.out.println("It seems that there are no tasks to list!");
        System.out.println("Try again after adding some tasks!");
        printDivider();
    }

    public static void printTimeParseErrorMessage() {
        printDivider();
        System.out.println("Time is in an incorrect format. Pls follow the given format <yyyy-MM-dd>");
        printDivider();
    }
}
