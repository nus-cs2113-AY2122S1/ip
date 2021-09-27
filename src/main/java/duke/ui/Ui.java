package duke.ui;

import duke.tasks.Task;
import duke.tasks.TaskList;

import java.util.Scanner;

/**
 * Ui class handles all user interaction in Duke
 */
public class Ui {
    private static final String BORDER = "_________________________________________________________________\n";
    Scanner sc = new Scanner(System.in);

    /**
     * Display logo and greeting message on program startup.
     */
    public static void displayGreetingMessage() {
        String LOGO = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println(LOGO);

        String output = " Hello! I'm Duke\n"
                + " What can I do for you?\n"
                + " Type `help` to display help message\n";
        printOutput(output);
    }

    /**
     * Beautify output by printing it along with a custom border.
     *
     * @param output Output to print
     */
    public static void printOutput(String output) {
        String niceOutput = BORDER + output + BORDER;
        System.out.println(niceOutput);
    }

    /**
     * Read user input from command line.
     *
     * @return userInput
     */
    public String readCommand() {
        String userInput = sc.nextLine();
        return userInput;
    }

    public static void acknowledgeAddedTask(Task addedTask, int taskListSize) {
        String output = " Got it. I've added this task:\n"
                + "   " + addedTask + "\n"
                + " Now you have " + taskListSize + " tasks in the list.\n";
        printOutput(output);
    }

    public static void displayDelimiterErrorMessage() {
        String output = " ☹ OOPS!!! Could not find delimiter\n";
        printOutput(output);
    }

    public static void displayTaskDoesNotExistMessage() {
        String output = " ☹ OOPS!!! The task specified does not exist.\n";
        printOutput(output);
    }

    public static void displayDeleteMessage(Task taskToDelete, int taskListSize) {
        String output = " Noted. I've removed this task:\n"
                + "   " + taskToDelete + "\n"
                + " Now you have " + taskListSize + " tasks in the list.\n";
        printOutput(output);
    }

    public static void displayDoneMessage(TaskList tasks, int taskNumber) {
        String output = " Nice! I've marked this task as done:\n"
                + "   " + tasks.getTaskAtIndex(taskNumber).toString() + "\n";
        printOutput(output);
    }

    public static void displayByeMessage() {
        String output = " Bye. Hope to see you again soon!\n";
        printOutput(output);
    }

    public static void displayUnknownCommandResponse() {
        String output = " ☹ OOPS!!! I'm sorry, but I don't know what that means :-(\n";
        printOutput(output);
    }

    public static void displayEmptyDescriptionResponse() {
        String output = " ☹ OOPS!!! The description of a task cannot be empty.\n";
        printOutput(output);
    }

    public static void displayEmptyKeywordResponse() {
        String output = " ☹ OOPS!!! Please input a keyword to find task\n";
        printOutput(output);
    }

    public static void displaySpecifyIntegerResponse() {
        String output = " ☹ OOPS!!! Please specify an integer.\n";
        printOutput(output);
    }

    public static void displayInvalidDateTimeFormatResponse() {
        String output = " ☹ OOPS!!! Please follow date time format: yyyy-MM-dd HH:mm\n";
        printOutput(output);
    }

    public static void displayInvalidDateFormatResponse() {
        String output = " ☹ OOPS!!! Please follow date format: yyyy-MM-dd\n";
        printOutput(output);
    }
}
