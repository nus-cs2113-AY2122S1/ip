package duke;

import duke.exception.EmptyArgumentException;
import duke.exception.EmptyParameterException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

import java.util.Scanner;
import java.util.ArrayList;

public class Duke {

    /**
     * The input scanner for the program
     */
    public static final Scanner SCANNER_INPUT = new Scanner(System.in);

    /**
     * An array that stores all the tasks
     */
    public static ArrayList<Task> taskList;

    /**
     * Stores the most recent input from the user in String format
     */
    public static String strInput = "";

    /**
     * "false" by default. "true" when session with duke is to be ended.
     */
    public static boolean isSessionEnding = false;

    public static void main(String[] args) {
        initTaskList();
        printGreetingMessage();

        //Main loop
        while (!isSessionEnding) {
            strInput = SCANNER_INPUT.nextLine();
            String command = extractCommand(strInput);
            switch (command) {
            case "bye":
                isSessionEnding = true;
                printGoodbyeMessage();
                break;

            case "list":
                printTaskList();
                break;

            case "done":
                try {
                    markTaskAsDone(extractContent(strInput));
                } catch (NumberFormatException e) { //not a number
                    printBorder();
                    System.out.println("Not a number!");
                    printBorder();
                } catch (EmptyArgumentException e) {
                    printBorder();
                    System.out.println("Please specify the index of the task to be marked as done!");
                    System.out.println("use: done [task index]");
                    printBorder();
                }
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
        printBorder();

        try {
            String content = extractContent(input);
            String descr = extractDescrFromEvent(content);
            String at = extractAtFromEvent(content);
            taskList.add(new Event(descr, at));
            printTaskAddedMessage();
        } catch (EmptyArgumentException e) {
            System.out.println("Please specify the event details!");
            System.out.println("use: event [description] /at [date]");
        } catch (EmptyParameterException e) {
            System.out.println("use: event [description] /at [date]");
        }
        printBorder();
    }

    /**
     * Adds a new Deadline type task to the taskList
     *
     * @param input the input string
     */
    private static void addDeadline(String input) {

        printBorder();
        try {
            String content = extractContent(input);
            String descr = extractDescrFromDeadline(content);
            String by = extractByFromDeadline(content);
            taskList.add(new Deadline(descr, by));
            printTaskAddedMessage();
        } catch (EmptyArgumentException e) {
            System.out.println("Please specify the deadline details!");
            System.out.println("use: deadline [description] /by [date]");
        } catch (EmptyParameterException e) {
            System.out.println("use: deadline [description] /by [date]");
        }
        printBorder();
    }

    /**
     * Adds a new Todo type task to the taskList
     *
     * @param input the input string
     */
    private static void addTodo(String input) {
        printBorder();
        try {
            String content = extractContent(input);
            taskList.add(new Todo(content));
            printTaskAddedMessage();
        } catch (EmptyArgumentException e) {
            System.out.println("Please specify the todo description!");
            System.out.println("use: todo [description]");
        }
        printBorder();
    }

    /**
     * Prints the success message for the most recently added task
     */
    private static void printTaskAddedMessage() {
        System.out.println("Got it. I've added this task:");
        System.out.println("  " + taskList.get(Task.getTotalTasks() - 1));
        System.out.println("Now you have " + Task.getTotalTasks() + " tasks in the list.");
    }

    /**
     * Extracts the date from the content of an event input (everything after "/at")
     *
     * @param input content of the event input
     * @return the date of the event
     */
    private static String extractAtFromEvent(String input) throws EmptyParameterException {
        int positionOfSeparator = input.trim().indexOf("/at");
        if ((positionOfSeparator + 3) >= input.length()) {
            throw new EmptyParameterException("event date");
        }
        return input.substring(positionOfSeparator + 3).trim();
    }

    /**
     * Extracts the description from the content of an event input (everything before "/at")
     *
     * @param input content of the event input
     * @return the description of the event
     */
    private static String extractDescrFromEvent(String input) throws EmptyParameterException {
        int positionOfSeparator = input.trim().indexOf("/at");
        if (positionOfSeparator <= 0) {
            throw new EmptyParameterException("event description");
        }
        return input.substring(0, positionOfSeparator - 1).trim();
    }

    /**
     * Extracts the deadline from the content of a deadline input (everything after "/by")
     *
     * @param input content of the deadline input
     * @return the deadline of the deadline
     */
    private static String extractByFromDeadline(String input) throws EmptyParameterException {
        int positionOfSeparator = input.trim().indexOf("/by");
        if ((positionOfSeparator + 3) >= input.length()) {
            throw new EmptyParameterException("deadline date");
        }
        return input.substring(positionOfSeparator + 3).trim();
    }

    /**
     * Extracts the description from the content of a deadline input (everything before "/by")
     *
     * @param input content of the deadline input
     * @return the description of the deadline
     */
    private static String extractDescrFromDeadline(String input) throws EmptyParameterException {
        int positionOfSeparator = input.trim().indexOf("/by");
        if (positionOfSeparator <= 0) {
            throw new EmptyParameterException("deadline description");
        }
        return input.substring(0, positionOfSeparator - 1).trim();
    }

    /**
     * Extracts the content from an input string (everything except the first word)
     *
     * @param input the input string
     * @return the input string with the first word excluded
     */
    private static String extractContent(String input) throws EmptyArgumentException {
        int firstSpacePosition = input.trim().indexOf(" ");
        if (firstSpacePosition < 0) {
            throw new EmptyArgumentException();
        }
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
     * @param taskNumber numerical ranking (as a string) of the task to be marked as done
     */
    private static void markTaskAsDone(String taskNumber) {
        int taskIndex = Integer.parseInt(taskNumber) - 1; //get the index of the task in the taskList array
        printBorder();
        try {
            taskList.get(taskIndex).setDone(true);
            System.out.println("Nice! I've marked this task as done: ");
            System.out.println("  " + taskList.get(taskIndex));
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Task number " + (taskIndex + 1) + " does not exist!");
        }
        printBorder();
    }

    /**
     * Prints out the list of tasks and their current status
     */
    private static void printTaskList() {
        printBorder();
        if (Task.getTotalTasks() == 0) {
            System.out.println("There are no tasks!");
            printBorder();
            return;
        }
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < Task.getTotalTasks(); i++) {
            System.out.println((i + 1) + "." + taskList.get(i));
        }
        printBorder();
    }

    /**
     * Initialize list of tasks
     */
    private static void initTaskList() {
        taskList = new ArrayList<>();
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
        System.out.println("Command not recognized!");
        System.out.println("try the following: \"list\", \"done\", \"todo\", \"deadline\", \"event\", \"bye\"");
        printBorder();
    }
}
