package duke.ui;

import duke.task.Task;

import java.util.ArrayList;
import java.util.List;

/**
 * All methods that display information to the user are in this class.
 */
public class Ui {

    private final static String DIVIDER = "_";

    private final static String NEW_HELLO = "Hi! I'm Duke. I've created your data file for you!";
    private final static String RETURNING_HELLO = "Welcome back!";
    private final static String PROMPT_COMMAND = "What would you like me to do? Use \"help\" if you're unsure!";

    private final static String HELP_MESSAGE = "Here are the commands I can currently execute:\n" +
            "\n" +
            "    todo XXX - Adds a Todo task XXX to the tasklist\n" +
            "    deadline XXX /by YYY - Adds a Deadline task XXX to the list, due by YYY\n" +
            "    event XXX /at YYY - Adds an Event task XXX to the list, happening on YYY\n" +
            "    list - Shows all current tasks with their ID and status (done or incomplete)\n" +
            "    done XXX - Toggles the status of the task with ID XXX between done and incomplete\n" +
            "    delete XXX - Deletes task with ID XXX from the list\n" +
            "    find XXX - Shows tasks containing the given keyword(s) (can be more than or equal to 1)\n" +
            "    date YYY - Shows tasks due by or happening on the date and time YYY\n" +
            "    bye - Exits Duke\n" +
            "\n" +
            "NOTE: All dates and times YYY must be entered in the following format:\n" +
            "dd/mm/yyyy hhmm (time is in 24-hour format)";

    private final static String LIST_MESSAGE = "Here are your current tasks and their status:";
    private static final String EMPTY_LIST_MESSAGE = "Your tasklist is currently empty!";
    private final static String ADD_MESSAGE_START = "Okay, I've added that task to your list:";
    private final static String COUNT_MESSAGE_START = "Now you have ";
    private final static String COUNT_MESSAGE_END = " task(s) in the list.";
    private final static String DONE_MESSAGE = "Nice! I've marked this task as done:";
    private final static String UNDONE_MESSAGE = "I've unmarked this task as done:";
    private final static String DELETE_MESSAGE = "Okay, I've deleted that task!";
    private final static String FIND_MESSAGE_START = "I found these tasks for ";
    private final static String FIND_MESSAGE_END = ":";
    private final static String DATE_MESSAGE_START = "Here are the tasks happening on/ due by ";
    private static final String BYE_MESSAGE = "Bye! I hope to see you again soon :)";

    private final static String EMPTY_COMMAND_MESSAGE = "That command was incomplete!";
    private final static String ILLEGAL_COMMAND_MESSAGE = "That's not a known command format!";
    private final static String IOEXCEPTION_MESSAGE = "Something went wrong while creating/loading your data: ";
    private final static String INDEX_OUT_OF_BOUNDS_MESSAGE = "That's not a valid task number!";
    private static final String DATE_TIME_ERROR_MESSAGE = "That date and/or time was in the wrong format!";
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
        println(DIVIDER.repeat(80));
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
        if (tasks.isEmpty()) {
            println(EMPTY_LIST_MESSAGE);
            println(PROMPT_COMMAND);
            printDivider();
            return;
        }
        println(LIST_MESSAGE);
        for (Task task : tasks) {
            println((tasks.indexOf(task) + 1) + ". " + task);
        }
        println(PROMPT_COMMAND);
        printDivider();
    }

    public static void printHelp() {
        printDivider();
        println(HELP_MESSAGE);
        printDivider();
    }

    /** Prints a message upon exiting the program. */
    public static void printByeMessage() {
        printDivider();
        println(BYE_MESSAGE);
        printDivider();
    }

    /**
     * Prints the current tasklist, or tells the user if the list is empty.
     *
     * @param tasks the current tasklist.
     */
    public static void printList(ArrayList<Task> tasks) {
        printDivider();
        if (tasks.isEmpty()) {
            println(EMPTY_LIST_MESSAGE);
            printDivider();
            return;
        }
        println(LIST_MESSAGE);
        for (Task task : tasks) {
            println((tasks.indexOf(task) + 1) + ". " + task);
        }
        printDivider();
    }

    /**
     * Prints the results found when the user searches for a keyword or date.
     *
     * @param tasks the current tasklist
     * @param searchResults the results found from the search
     * @param keyword the keyword or date for which the results were found
     * @param isDate true if the keyword is a date
     */
    public static void printResults(ArrayList<Task> tasks, List<Task> searchResults, String keyword, boolean isDate) {
        printDivider();
        if (searchResults.isEmpty()) {
            println(NO_RESULTS_MESSAGE_START + keyword + NO_RESULTS_MESSAGE_END);
            return;
        }
        String toPrint = isDate ? DATE_MESSAGE_START : FIND_MESSAGE_START;
        println(toPrint + keyword + FIND_MESSAGE_END);
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
        println(ADD_MESSAGE_START);
        println(tasks.get(Task.getTaskCount() - 1));
        println(COUNT_MESSAGE_START + Task.getTaskCount() + COUNT_MESSAGE_END);
        printDivider();
    }

    /**
     * Notifies the user whether a task has been marked as done or incomplete.
     *
     * @param taskIndex the task number to be marked
     * @param tasks the current task list
     * @param isDone the new status of the task given
     */
    public static void printDoneMessage(int taskIndex, ArrayList<Task> tasks, boolean isDone) {
        String toPrint = isDone ? DONE_MESSAGE : UNDONE_MESSAGE;
        printDivider();
        println(toPrint);
        println(tasks.get(taskIndex - 1));
        printDivider();
    }

    /**
     * Notifies the user that the task given by the user has been deleted from the list.
     *
     * @param deletedTask the task object that was deleted
     */
    public static void printDeleteMessage(Task deletedTask) {
        printDivider();
        println(DELETE_MESSAGE);
        println(deletedTask);
        println(COUNT_MESSAGE_START + Task.getTaskCount() + COUNT_MESSAGE_END);
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
        println(DATE_TIME_ERROR_MESSAGE);
        printDivider();
    }
}