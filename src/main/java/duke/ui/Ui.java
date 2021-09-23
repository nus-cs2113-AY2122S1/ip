package duke.ui;

import duke.task.Task;

import java.util.ArrayList;
import java.util.List;

/**
 * All methods that display information to the user are in this class.
 */
public class Ui {

    private final static String DIVIDER = "-----------------------------------------------------";

    private final static String NEW_HELLO = "Hi! I'm Duke. I've created your data file for you!";
    private final static String RETURNING_HELLO = "Welcome back! Here are your current tasks and their status:";
    private final static String PROMPT_COMMAND = "What would you like me to do?";

    private final static String LIST_MESSAGE = "Here are your current tasks and their status:";
    private final static String ADD_MESSAGE = "Okay, I've added that task to your list:";
    private final static String DONE_MESSAGE = "Nice! I've marked this task as done:";
    private final static String DELETE_MESSAGE = "Okay, I've deleted that task!";
    private final static String FIND_MESSAGE_START = "I found these tasks for \"";
    private final static String FIND_MESSAGE_END = "\":";
    private static final String BYE_MESSAGE = "Bye! I hope to see you again soon :)";

    private final static String EMPTY_COMMAND_MESSAGE = "That command was incomplete!";
    private final static String ILLEGAL_COMMAND_MESSAGE = "That's not a known command format!";
    private final static String IOEXCEPTION_MESSAGE = "Something went wrong while creating/loading your data: ";
    private final static String INDEX_OUT_OF_BOUNDS_MESSAGE = "That's not a valid task number!";
    private static final String DATE_TIME_PARSE_MESSAGE = "That date and/or time was in the wrong format!";
    private static final String NO_RESULTS_MESSAGE_START = "I could not find any results for \"";
    private static final String NO_RESULTS_MESSAGE_END = "\"!";

    private static <T> void print(T line) {
        System.out.print(line);
    }

    private static <T> void println(T line) {
        System.out.println(line);
    }

    /** Prints a divider to separate user input from Duke's output. */
    public static void printDivider() {
        println(DIVIDER);
    }


    /** Prints a greeting message for first time users. */
    public static void printNewHello() {
        printDivider();
        println(NEW_HELLO);
        println(PROMPT_COMMAND);
        printDivider();
    }

    /**
     * Prints a greeting message to returning users and displays the current task list.
     *
     * @param tasks the current task list
     */
    public static void printReturningHello(ArrayList<Task> tasks) {
        printDivider();
        println(RETURNING_HELLO);
        for (Task task : tasks) {
            println((tasks.indexOf(task) + 1) + ". " + task);
        }
        println(PROMPT_COMMAND);
        printDivider();
    }

    /** Prints a message upon exiting the program. */
    public static void printByeMessage() {
        printDivider();
        println(BYE_MESSAGE);
        printDivider();
    }

    /**
     * Prints the current tasklist.
     *
     * @param tasks the current tasklist.
     */
    public static void printList(ArrayList<Task> tasks) {
        printDivider();
        println(LIST_MESSAGE);
        for (Task task : tasks) {
            println((tasks.indexOf(task) + 1) + ". " + task);
        }
        printDivider();
    }

    /**
     * Prints the results found when the user searches for a keyword.
     *
     * @param tasks the current tasklist
     * @param searchResults the results found from the search
     * @param keyword the keyword for which the results were found
     */
    public static void printResults(ArrayList<Task> tasks, List<Task> searchResults, String keyword) {
        printDivider();
        if (searchResults.isEmpty()) {
            println(NO_RESULTS_MESSAGE_START + keyword + NO_RESULTS_MESSAGE_END);
            return;
        }
        println(FIND_MESSAGE_START + keyword + FIND_MESSAGE_END);
        for (Task result : searchResults) {
            print((tasks.indexOf(result) + 1) + ". ");
            println(result);
        }
    }

    /**
     * Notifies the user that a task has been added to the tasklist.
     *
     * @param tasks the current task list
     */
    public static void printAddMessage(ArrayList<Task> tasks) {
        printDivider();
        println(ADD_MESSAGE);
        println(tasks.get(Task.getTaskCount() - 1));
        printDivider();
    }

    /**
     * Notifies the user that a task has been marked as done.
     *
     * @param tasks the current task list
     */
    public static void printDoneMessage(ArrayList<Task> tasks) {
        printDivider();
        println(DONE_MESSAGE);
        println(tasks.get(Task.getTaskCount() - 1));
        printDivider();
    }

    /**
     * Notifies the user that the task given by the user has been deleted from the list.
     *
     * @param taskIndex the index of the task to be deleted
     * @param tasks the current task list
     */
    public static void printDeleteMessage(int taskIndex, ArrayList<Task> tasks) {
        printDivider();
        println(DELETE_MESSAGE);
        println(tasks.get(taskIndex - 1));
        printDivider();
    }

    /** Notifies the user that the command entered was incomplete. */
    public static void printEmptyCommandMessage() {
        printDivider();
        println(EMPTY_COMMAND_MESSAGE);
        printDivider();
    }

    /** Notifies the user that the command entered was unknown. */
    public static void printIllegalCommandMessage() {
        printDivider();
        println(ILLEGAL_COMMAND_MESSAGE);
        printDivider();
    }

    /** Notifies the user that an error occurred reading/writing to the data file. */
    public static void printIOExceptionMessage(Exception e) {
        printDivider();
        println(IOEXCEPTION_MESSAGE + e.getMessage());
        printDivider();
    }

    /** Notifies the user that the task index entered was not in the list */
    public static void printIndexOutOfBoundsMessage() {
        printDivider();
        println(INDEX_OUT_OF_BOUNDS_MESSAGE);
        printDivider();
    }

    /** Notifies the user that the date and/or time they entered was of the wrong format. */
    public static void printDateTimeParseMessage() {
        printDivider();
        System.out.println(DATE_TIME_PARSE_MESSAGE);
        printDivider();
    }
}