package processing;

import org.jetbrains.annotations.NotNull;
import tasks.Task;
import java.util.Scanner;

public class UI {
    private static final Scanner myScan = new Scanner(System.in);

    /*----------- CONSOLE LOGGING ----------- */
    private static final String ADD_TASK = "Got it. I've added this task: ";
    private static final String DELETE_TASK = "Noted. I've removed this task: ";
    private static final String LIST_COMMANDS = "These are the valid commands: ";

    private static final String DTFORMAT_HEADER = "Here are the valid DateTime formats to use: ";

    public static final String FAREWELL_STR = "Bye. Hope to see you again soon!";
    private static final String DIVIDER = "------------------------------------------";
    private static final String BEGIN_STR = "What can I do for you?";
    private static final String LOGO = " ____        _        \n" +
                                       "|  _ \\ _   _| | _____ \n" +
                                       "| | | | | | | |/ / _ \\\n" +
                                       "| |_| | |_| |   <  __/\n" +
                                       "|____/ \\__,_|_|\\_\\___|\n";
    private static final String GREETING = "_________________________\n" +
                                           "Hello! I'm Duke.Duke\n" +
                                           "Standby while I load up your schedule\n" +
                                           "Loading...";

    private static void showNumTasks(Task t, int taskSize) {
        System.out.println(t);
        System.out.println("You now have (" + taskSize + ") tasks!" );
    }

    /*----------- PUBLIC FUNCTIONS --------------- */

    /**
     * Greets the user on startup
     */
    public static void greetPartOne() {
        System.out.println("Hello from\n" + LOGO);
        System.out.println(GREETING);

    }

    /**
     * Wraps up the greeting and prompts for user input
     */
    public static void greetPartTwo() {
        printDivider();
        System.out.println(BEGIN_STR);
    }

    /**
     * Prints a divider line to prettify the UI
     */
    public static void printDivider() {
        System.out.println(DIVIDER);
    }

    /**
     * Prints the exception if an error occurred
     * @param e Exception that occurred
     */
    public static void showError(@NotNull Exception e) {
        e.printStackTrace();
        try {
            Thread.sleep(200);
        } catch (InterruptedException e2){
            Thread.currentThread().interrupt();
        }
    }

    /**
     * Print the added task
     * @param t Task that has been added
     * @param taskSize Number of tasks now on the list
     */
    public static void showAddTask (Task t, int taskSize) {
        System.out.println(ADD_TASK);
        showNumTasks(t, taskSize);
    }

    /**
     * Prints the deleted task
     * @param t Task that has been deleted
     * @param taskSize Number of tasks left on the list
     */
    public static void showDeleteTask(Task t, int taskSize) {
        System.out.println(DELETE_TASK);
        showNumTasks(t, taskSize);
    }

    /**
     * Takes in a user input
     * @return String representation of user input
     */
    public static @NotNull String getCommand() {
        System.out.println("");
        String input = myScan.nextLine();
        if (input.isBlank()) {
            return getCommand();
        }
        return input;
    }

    /**
     * Prints the closing statement before exiting application
     */
    public static void close() {
        System.out.println(FAREWELL_STR);
        myScan.close();
    }

    /**
     * List all the valid Date Time Formats
     */
    public static void listDTFormats() {
        System.out.println(DTFORMAT_HEADER);
        for (String format : DateParser.DATETIME_FORMATS) {
            System.out.println("-> " + format);
        }
        for (String format : DateParser.TIME_FORMATS) {
            System.out.println("-> today " + format);
        }
    }

    /**
     * Lists all the commands accepted by DUKE
     */
    public static void listCommands() {
        System.out.println(LIST_COMMANDS);
        for (String command : CommandHandler.commands) {
            System.out.println(" > " + command);
        }
    }
}
