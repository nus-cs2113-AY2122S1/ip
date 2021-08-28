import java.util.Scanner;

public class Duke {

    /**
     * Maximum number of Tasks that can be stored
     */
    public static final int MAX_TASKS = 100;

    /**
     * The input scanner for the program
     */
    public static final Scanner SCANNER_INPUT = new Scanner(System.in);

    /**
     * An array that stores all the tasks
     */
    public static Task[] taskList;

    /**
     * Stores the most recent input from the user in String format
     */
    public static String strInput = "";

    /**
     * "false" by default. "true" when session with duke is to be ended.
     */
    public static boolean hasSessionEnded = false;

    public static void main(String[] args) {
        initTaskList();
        printGreetingMessage();

        //Main loop
        while (!hasSessionEnded) {
            strInput = SCANNER_INPUT.nextLine();
            String command = extractCommand(strInput);
            switch (command) {
            case "bye":
                hasSessionEnded = true;
                printGoodbyeMessage();
                break;

            case "list":
                printTaskList();
                break;

            case "done":
                markTaskAsDone(extractContent(strInput));
                break;

            case "todo":
                addTodo(strInput);
                break;

            case "deadline":
                addDeadline(strInput);
                break;

            case "event":
                addEvent(strInput);
                break;

            default:
                printInvalidCommand();
                break;
            }
        }
    }

    /*
     * ===============================================
     *           Task class related functions
     * ===============================================
     */

    /**
     * Adds a new Event type task to the taskList
     *
     * @param input the input string
     */
    private static void addEvent(String input) {
        String content = extractContent(input);
        taskList[Task.getTotalTasks()] = new Event(extractDescrFromEventContent(content),
                extractAtFromEventContent(content));
        printTaskAddedMessage();
    }

    /**
     * Adds a new Deadline type task to the taskList
     *
     * @param input the input string
     */
    private static void addDeadline(String input) {
        String content = extractContent(input);
        taskList[Task.getTotalTasks()] = new Deadline(extractDescrFromDeadlineContent(content),
                extractByFromDeadlineContent(content));
        printTaskAddedMessage();
    }

    /**
     * Adds a new Todo type task to the taskList
     *
     * @param input the input string
     */
    private static void addTodo(String input) {
        taskList[Task.getTotalTasks()] = new Todo(extractContent(input));
        printTaskAddedMessage();
    }

    /**
     * Prints the success message for the most recently added task
     */
    private static void printTaskAddedMessage() {
        printBorder();
        System.out.println("Got it. I've added this task:");
        System.out.println("  " + taskList[Task.getTotalTasks() - 1]);
        System.out.println("Now you have " + Task.getTotalTasks() + " tasks in the list.");
        printBorder();
    }

    /**
     * Extracts the date from the content of an event input (everything after "/at")
     *
     * @param input content of the event input
     * @return the date of the event
     */
    private static String extractAtFromEventContent(String input) {
        int positionOfSeparator = input.trim().indexOf("/at");
        return input.substring(positionOfSeparator + 3).trim();
    }

    /**
     * Extracts the description from the content of an event input (everything before "/at")
     *
     * @param input content of the event input
     * @return the description of the event
     */
    private static String extractDescrFromEventContent(String input) {
        int positionOfSeparator = input.trim().indexOf("/at");
        return input.substring(0, positionOfSeparator - 1).trim();
    }

    /**
     * Extracts the deadline from the content of a deadline input (everything after "/by")
     *
     * @param input content of the deadline input
     * @return the deadline of the deadline
     */
    private static String extractByFromDeadlineContent(String input) {
        int positionOfSeparator = input.trim().indexOf("/by");
        return input.substring(positionOfSeparator + 3).trim();
    }

    /**
     * Extracts the description from the content of a deadline input (everything before "/by")
     *
     * @param input content of the deadline input
     * @return the description of the deadline
     */
    private static String extractDescrFromDeadlineContent(String input) {
        int positionOfSeparator = input.trim().indexOf("/by");
        return input.substring(0, positionOfSeparator - 1).trim();
    }

    /**
     * Extracts the content from an input string (everything except the first word)
     *
     * @param input the input string
     * @return the input string with the first word excluded
     */
    private static String extractContent(String input) {
        int firstSpacePosition = input.trim().indexOf(" ");
        return input.substring(firstSpacePosition + 1).trim();
    }

    /**
     * Extracts the command from an input string (the first word)
     *
     * @param input the input string
     * @return first word of the input string
     */
    private static String extractCommand(String input) {
        String[] words = input.trim().split(" ");
        return words[0].trim();
    }

    /**
     * Marks the task of the given ranking as done
     *
     * @param word numerical ranking of the task to be marked as done (as a string)
     */
    private static void markTaskAsDone(String word) {
        int taskIndex = Integer.parseInt(word) - 1; //get the index of the task in the taskList array
        taskList[taskIndex].setDone(true);
        printBorder();
        System.out.println("Nice! I've marked this task as done: ");
        System.out.println("  " + taskList[taskIndex]);
        printBorder();
    }

    /**
     * Prints out the list of tasks and their current status
     */
    private static void printTaskList() {
        printBorder();
        if (Task.getTotalTasks() == 0) {
            System.out.println("There are no tasks added yet!");
            printBorder();
            return;
        }
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < Task.getTotalTasks(); i++) {
            System.out.println((i + 1) + "." + taskList[i]);
        }
        printBorder();
    }

    /**
     * Initialize list of tasks
     */
    private static void initTaskList() {
        taskList = new Task[MAX_TASKS];
    }

    /*
     * ===============================================
     *         Basic message printing functions
     * ===============================================
     */

    /**
     * Prints a border made of "_" characters
     */
    private static void printBorder() {
        System.out.println("____________________________________________________________");
    }

    private static void printGreetingMessage() {
        printBorder();
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
        printBorder();
    }

    private static void printGoodbyeMessage() {
        printBorder();
        System.out.println("Bye. Hope to see you again soon!");
        printBorder();
    }

    /**
     * Prints the error message for an invalid command
     */
    private static void printInvalidCommand() {
        printBorder();
        System.out.println("Command not recognized");
        printBorder();
    }
}
