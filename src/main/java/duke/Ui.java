package duke;

import duke.task.Task;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Deals with interactions with the user.
 */
public class Ui {

    //Application Logo
    private static final String LOGO = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";

    //Utility constants
    private static final String INDENTATION = "\t";
    private static final int HORIZONTAL_LINE_LENGTH = 100;

    //General Messages
    private static final String MESSAGE_START_APPLICATION = "Hello from\n" + LOGO + System.lineSeparator() +
            "What can I do for you?";
    private static final String MESSAGE_EXIT_APPLICATION = "Thank you for using our application. " +
            "We hope to see you again soon";
    private static final String MESSAGE_INVALID_COMMAND = "I am sorry but I am not able to " +
            "recognise this command";
    private static final String MESSAGE_SAVE_TASK_LIST = "You task list has been saved successfully";
    private static final String MESSAGE_TASK_ADDED_SUCCESSFULLY = "The following task has been added:";
    private static final String MESSAGE_MARK_TASK_SUCCESS = "The following task has been marked as done:";
    private static final String MESSAGE_DELETE_TASK_SUCCESS = "The following task has been deleted:";
    private static final String MESSAGE_NO_TASK_AVAILABLE = "You have no tasks yet";
    private static final String MESSAGE_PRINT_ALL_TASK_SUCCESS = "Here are all your tasks:";
    private static final String MESSAGE_NO_MATCHING_TASK = "There are no matching tasks available";
    private static final String MESSAGE_FIND_TASK_SUCCESS = "Here are all the matching tasks";

    //Error Messages
    private static final String ERROR_NO_TASK_NUMBER_TO_MARK = "Please provide a task number e.g 'xxxx 2'";
    private static final String ERROR_INVALID_TASK_NUMBER = "Sorry, but the task does not exist, " +
            "unable to proceed with command.\n" +
            "You can view a list of your tasks using the 'list' command";
    private static final String ERROR_INVALID_TASK_NUMBER_FORMAT = "There seems to be an issue with " +
            "the format of the task number.\n " +
            "Please try again with the correct format (e.g xxxx 3)";
    private static final String ERROR_TODO_NO_DESCRIPTION = "Todo tasks require a description " +
            "e.g 'todo CS1010 Assignment'";
    private static final String ERROR_DEADLINE_NO_DESCRIPTION = "Deadlines require a description " +
            "e.g 'deadline Project Reflection /by Friday 10pm'";
    private static final String ERROR_EVENT_NO_DESCRIPTION = "Events require a description " +
            "e.g 'event Seminar /at Friday 2pm'";
    private static final String ERROR_WRITING_TO_SAVE_FILE = "Error writing to save file";
    private static final String ERROR_CREATING_SAVE_FILE = "Error creating save file";
    private static final String ERROR_CREATING_DATA_DIRECTORY = "Error creating data directory";
    private static final String ERROR_DUKE_UNKNOWN = "Unknown error with Duke occurred";
    private static final String ERROR_READING_SAVE_FILE = "Error reading save file, " +
            "some tasks may have been lost";
    private static final String ERROR_CONVERTING_SAVE_FILE = "Error converting file to task list, " +
            "some tasks may have been lost";
    private static final String ERROR_EMPTY_QUERY = "The query is empty. Please add a valid query";
    private static final String ERROR_DATE_TIME_FORMAT = "There is an error with the date time " +
            "format provided, please use a valid date time format\n" +
            "(e.g yyyy-mm-dd HH:mm)";
    private static final String ERROR_EVENT_FORMAT = "There is an error with the format of the input. " +
            "Please enter a valid input\n" +
            "(e.g event Lecture /at 2019-08-13 20:00)";
    private static final String ERROR_DEADLINE_FORMAT = "There is an error with the format of the input. " +
            "Please enter a valid input\n " +
            "(e.g deadline Assignment /by 2021-08-23 13:00)";

    /**
     * Scanner object to read user input
     */
    private final Scanner in;

    /**
     * Constructs an instance of the <code>Ui</code> object.
     * Only required when reading input from user, as all other methods
     * are static methods.
     */
    public Ui() {
        in = new Scanner(System.in);
    }

    //Reading input

    /**
     * Reads input from the user.
     *
     * @return Input by the user as a String.
     */
    public String readInput() {
        return in.nextLine();
    }

    //Utility print methods
    private static void printHorizontalLine() {
        for (int i = 0; i < HORIZONTAL_LINE_LENGTH; i++) {
            System.out.print("-");
        }
        System.out.println();
    }

    private static void printSpacing() {
        System.out.print(INDENTATION);
    }

    private static void printGenericMessage(String message) {
        printHorizontalLine();
        System.out.println(message);
        printHorizontalLine();
    }

    //General Messages
    /**
     * Prints a welcome message to the user.
     */
    public static void printWelcomeMessage() {
        System.out.println(MESSAGE_START_APPLICATION);
        Ui.printHorizontalLine();
    }

    /**
     * Prints a farewell message to the user.
     */
    public static void printExitProgramMessage() {
        printGenericMessage(MESSAGE_EXIT_APPLICATION);
    }

    /**
     * Prints a message indicating that tasks were saved successfully.
     */
    public static void printSaveTaskMessage() {
        printGenericMessage(MESSAGE_SAVE_TASK_LIST);
    }

    /**
     * Prints a message indicating that a task was added successfully.
     *
     * @param listLength Length of the task list.
     * @param taskAdded  The task that was added.
     */
    public static void printAddTaskMessage(int listLength, Task taskAdded) {
        printHorizontalLine();
        System.out.println(MESSAGE_TASK_ADDED_SUCCESSFULLY);
        Ui.printSpacing();
        System.out.println(taskAdded);
        System.out.println("You now have " + listLength + " task(s)");
        printHorizontalLine();
    }

    /**
     * Prints a message indicating that a task was deleted successfully.
     *
     * @param taskDeleted The task that was deleted.
     */
    public static void printDeleteTaskSuccessMessage(Task taskDeleted) {
        printHorizontalLine();
        System.out.println(MESSAGE_DELETE_TASK_SUCCESS);
        printSpacing();
        System.out.println(taskDeleted);
        printHorizontalLine();
    }

    /**
     * Prints a message indicating that a task was marked as done successfully.
     *
     * @param taskMarked The task that was marked.
     */
    public static void printMarkTaskSuccessMessage(Task taskMarked) {
        printHorizontalLine();
        System.out.println(MESSAGE_MARK_TASK_SUCCESS);
        printSpacing();
        System.out.println(taskMarked);
        printHorizontalLine();
    }

    /**
     * Prints all matching tasks from a query.
     *
     * @param matchingTasks List of matching tasks.
     */
    public static void printMatchingTasks(ArrayList<Task> matchingTasks) {
        printHorizontalLine();
        if (matchingTasks.isEmpty()) {
            System.out.println(MESSAGE_NO_MATCHING_TASK);
            printHorizontalLine();
            return;
        }
        System.out.println(MESSAGE_FIND_TASK_SUCCESS);
        for (int i = 0; i < matchingTasks.size(); i++) {
            printSpacing();
            System.out.println((i + 1) + ". " + matchingTasks.get(i));
        }
        printHorizontalLine();

    }

    /**
     * Prints all the task in a task list.
     *
     * @param tasks ArrayList containing all tasks.
     */
    public static void printAllTasks(ArrayList<Task> tasks) {
        printHorizontalLine();
        if (tasks.isEmpty()) {
            System.out.println(MESSAGE_NO_TASK_AVAILABLE);
            printHorizontalLine();
            return;
        }
        System.out.println(MESSAGE_PRINT_ALL_TASK_SUCCESS);
        for (int i = 0; i < tasks.size(); i++) {
            printSpacing();
            System.out.println((i + 1) + ". " + tasks.get(i));
        }
        printHorizontalLine();
    }

    //Error messages split into multiple methods for better maintainability
    /**
     * Shows error when there is no description provided for a Todo task.
     */
    public static void showTodoDescriptionError() {
        printGenericMessage(ERROR_TODO_NO_DESCRIPTION);
    }

    /**
     * Shows error when there is no description provided for a Deadline task.
     */
    public static void showDeadlineDescriptionError() {
        printGenericMessage(ERROR_DEADLINE_NO_DESCRIPTION);
    }

    /**
     * Shows error when there is no description provided for an Event task.
     */
    public static void showEventDescriptionError() {
        printGenericMessage(ERROR_EVENT_NO_DESCRIPTION);
    }

    /**
     * Shows error when an input command is invalid.
     */
    public static void showInvalidCommandError() {
        printGenericMessage(MESSAGE_INVALID_COMMAND);
    }

    /**
     * Shows error when task number is not provided for certain commands.
     */
    public static void showNoTaskNumberError() {
        printGenericMessage(ERROR_NO_TASK_NUMBER_TO_MARK);
    }

    /**
     * Shows error when task number provided for certain commands is invalid.
     */
    public static void showInvalidTaskNumberError() {
        printGenericMessage(ERROR_INVALID_TASK_NUMBER);
    }

    /**
     * Shows error when there is an error with the format of the task number provided
     * for certain commands.
     */
    public static void showInvalidTaskNumberFormatError() {
        printGenericMessage(ERROR_INVALID_TASK_NUMBER_FORMAT);
    }

    /**
     * Shows error when there is an error with creating a file to save the task list.
     */
    public static void showCreateSaveFileError() {
        printGenericMessage(ERROR_CREATING_SAVE_FILE);
    }

    /**
     * Shows error when there is an error with creating a directory to save the task list file.
     */
    public static void showCreateDirectoryError() {
        printGenericMessage(ERROR_CREATING_DATA_DIRECTORY);
    }

    /**
     * Shows error when there is an error with converting the save file into a
     * <code>TaskList</code> object.
     */
    public static void showConvertSaveFileError() {
        printGenericMessage(ERROR_CONVERTING_SAVE_FILE);
    }

    /**
     * Shows error when there is an error with reading the save file from the given path.
     */
    public static void showReadSaveFileError() {
        printGenericMessage(ERROR_READING_SAVE_FILE);
    }

    /**
     * Shows error when there is error with writing to a save file.
     */
    public static void showWritingToSaveFileError() {
        printGenericMessage(ERROR_WRITING_TO_SAVE_FILE);
    }

    /**
     * Shows error when query input in empty.
     */
    public static void showEmptyQueryError() {
        printGenericMessage(ERROR_EMPTY_QUERY);
    }

    /**
     * Shows error when DateTime format is wrong.
     */
    public static void showDateTimeFormatError() {
        printGenericMessage(ERROR_DATE_TIME_FORMAT);
    }

    /**
     * Shows error when Event format is wrong.
     */
    public static void showEventFormatError() {
        printGenericMessage(ERROR_EVENT_FORMAT);
    }

    /**
     * Shows error when Deadline format is wrong.
     */
    public static void showDeadlineFormatError() {
        printGenericMessage(ERROR_DEADLINE_FORMAT);
    }

    /**
     * Shows error when an unknown error happens with Duke.
     */
    public static void showUnknownError() {
        printGenericMessage(ERROR_DUKE_UNKNOWN);
    }
}
