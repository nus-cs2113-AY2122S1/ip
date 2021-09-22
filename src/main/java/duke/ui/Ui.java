package duke.ui;

import duke.Duke;
import duke.task.Task;

public class Ui {

    private final static String DIVIDER = "-----------------------------------------------------";

    private final static String NEW_HELLO = "Hi! I'm Duke. I've created your data file for you, what would you like me to do?";
    private final static String RETURNING_HELLO = "Welcome back! Here are your current tasks and their status:";

    private final static String LIST_MESSAGE = "Here are your current tasks and their status:";
    private final static String ADD_MESSAGE = "Okay, I've added that task to your list:";
    private final static String DONE_MESSAGE = "Nice! I've marked this task as done:";
    private final static String DELETE_MESSAGE = "Okay, I've deleted that task!";
    private static final String BYE_MESSAGE = "Bye! I hope to see you again soon :)";

    private final static String EMPTY_COMMAND_MESSAGE = "Sorry, you didn't give me a fitting description for your task.";
    private final static String ILLEGAL_COMMAND_MESSAGE = "That's not a known command format!";
    private final static String IOEXCEPTION_MESSAGE = "Something went wrong while creating/loading your data: ";
    private final static String INDEX_OUT_OF_BOUNDS_MESSAGE = "That's not a valid task number!";
    private static final String DATE_TIME_PARSE_MESSAGE = "That date and/or time was in the wrong format!";

    public static void printDivider() {
        System.out.println(DIVIDER);
    }

    public static void printNewHello() {
        printDivider();
        System.out.println(NEW_HELLO);
        printDivider();
    }

    public static void printReturningHello() {
        printDivider();
        System.out.println(RETURNING_HELLO);
        for (Task task : Duke.tasks) {
            System.out.println((Duke.tasks.indexOf(task) + 1) + ". " + task);
        }
        printDivider();
    }

    public static void printByeMessage() {
        printDivider();
        System.out.println(BYE_MESSAGE);
        printDivider();
    }

    public static void printList() {
        printDivider();
        System.out.println(LIST_MESSAGE);
        for (Task task : Duke.tasks) {
            System.out.println((Duke.tasks.indexOf(task) + 1) + ". " + task);
        }
        printDivider();
    }

    public static void printAddMessage() {
        printDivider();
        System.out.println(ADD_MESSAGE);
        System.out.println(Duke.tasks.get(Task.getTaskCount() - 1));
        printDivider();
    }

    public static void printDoneMessage() {
        printDivider();
        System.out.println(DONE_MESSAGE);
        System.out.println(Duke.tasks.get(Task.getTaskCount() - 1));
        printDivider();
    }

    public static void printDeleteMessage(int taskIndex) {
        printDivider();
        System.out.println(DELETE_MESSAGE);
        System.out.println(Duke.tasks.get(taskIndex - 1));
        printDivider();
    }

    public static void printEmptyCommandMessage() {
        printDivider();
        System.out.println(EMPTY_COMMAND_MESSAGE);
        printDivider();
    }

    public static void printIllegalCommandMessage() {
        printDivider();
        System.out.println(ILLEGAL_COMMAND_MESSAGE);
        printDivider();
    }

    public static void printIOExceptionMessage(Exception e) {
        printDivider();
        System.out.println(IOEXCEPTION_MESSAGE + e.getMessage());
        printDivider();
    }

    public static void printIndexOutOfBoundsMessage() {
        printDivider();
        System.out.println(INDEX_OUT_OF_BOUNDS_MESSAGE);
        printDivider();
    }

    public static void printDateTimeParseMessage() {
        printDivider();
        System.out.println(DATE_TIME_PARSE_MESSAGE);
        printDivider();
    }
}