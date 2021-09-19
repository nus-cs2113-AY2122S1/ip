package duke;


import duke.task.*;

import java.util.ArrayList;
import java.util.Scanner;

public class Ui {

    //Application Logo
    private static final String LOGO = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";

    //Utilities
    private static final String INDENTATION = "\t";
    private static final int HORIZONTAL_LINE_LENGTH = 100;

    //General Messages
    private static final String MESSAGE_START_APPLICATION = "Hello from\n" + LOGO + System.lineSeparator() + "What can I do for you?";
    private static final String MESSAGE_EXIT_APPLICATION = "Thank you for using our application. We hope to see you again soon";
    private static final String MESSAGE_INVALID_COMMAND = "I am sorry but I am not able to recognise this command";
    private static final String MESSAGE_SAVE_TASK_LIST = "You task list has been saved successfully";
    private static final String MESSAGE_TASK_ADDED_SUCCESSFULLY = "The following task has been added:";
    private static final String MESSAGE_MARK_TASK_SUCCESS = "The following task has been marked as done:";
    private static final String MESSAGE_DELETE_TASK_SUCCESS = "The following task has been deleted:";
    private static final String MESSAGE_NO_TASK_AVAILABLE = "You have no tasks yet";
    private static final String MESSAGE_PRINT_ALL_TASK_SUCCESS = "Here are all your tasks:";

    //Error Messages
    private static final String ERROR_NO_TASK_NUMBER_TO_MARK = "Please provide a task number e.g 'xxxx 2'";
    private static final String ERROR_INVALID_TASK_NUMBER = "Sorry, but the task does not exist, unable to proceed with command.\n" +
            "You can view a list of your tasks using the 'list' command";
    private static final String ERROR_INVALID_TASK_NUMBER_FORMAT = "There seems to be an issue with the format of the task number.\n " +
            "Please try again with the correct format (e.g xxxx 3)";
    private static final String ERROR_TODO_NO_DESCRIPTION = "Todo tasks require a description e.g 'todo CS1010 Assignment'";
    private static final String ERROR_DEADLINE_NO_DESCRIPTION = "Deadlines require a description e.g 'deadline Project Reflection /by Friday 10pm'";
    private static final String ERROR_EVENT_NO_DESCRIPTION = "Events require a description e.g 'event Seminar /at Friday 2pm'";
    private static final String ERROR_WRITING_TO_SAVE_FILE = "Error writing to save file";
    private static final String ERROR_CREATING_SAVE_FILE = "Error creating save file";
    private static final String ERROR_CREATING_DATA_DIRECTORY = "Error creating data directory";
    private static final String ERROR_DUKE_UNKNOWN = "Unknown error with Duke occurred";
    private static final String ERROR_READING_SAVE_FILE = "Error reading save file, some tasks may have been lost";
    private static final String ERROR_CONVERTING_SAVE_FILE = "Error converting file to task list, some tasks may have been lost";

    private final Scanner in;

    public Ui() {
        in = new Scanner(System.in);
    }

    //Reading input
    public String readInput() {
        return in.nextLine();
    }

    //Utility methods
    private static void printHorizontalLine() {
        for (int i = 0; i < HORIZONTAL_LINE_LENGTH; i++) {
            System.out.print("-");
        }
        System.out.println();
    }

    private static void printSpacing() {
        System.out.print(INDENTATION);
    }

    public static void printGenericMessage(String message) {
        printHorizontalLine();
        System.out.println(message);
        printHorizontalLine();
    }

    //General Messages
    public static void printWelcomeMessage() {
        System.out.println(MESSAGE_START_APPLICATION);
        Ui.printHorizontalLine();
    }

    public static void printExitProgramMessage() {
        printGenericMessage(MESSAGE_EXIT_APPLICATION);
    }

    public static void printSaveTaskMessage() {
        printGenericMessage(MESSAGE_SAVE_TASK_LIST);
    }

    public static void printAddTaskMessage(int listLength, Task taskAdded) {
        printHorizontalLine();
        System.out.println(MESSAGE_TASK_ADDED_SUCCESSFULLY);
        Ui.printSpacing();
        System.out.println(taskAdded);
        System.out.println("You now have " + listLength + " task(s)");
        printHorizontalLine();
    }

    public static void printDeleteTaskSuccessMessage(Task taskDeleted) {
        printHorizontalLine();
        System.out.println(MESSAGE_DELETE_TASK_SUCCESS);
        printSpacing();
        System.out.println(taskDeleted);
        printHorizontalLine();
    }

    public static void printMarkTaskSuccessMessage(Task taskMarked) {
        printHorizontalLine();
        System.out.println(MESSAGE_MARK_TASK_SUCCESS);
        printSpacing();
        System.out.println(taskMarked);
        printHorizontalLine();
    }

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
    public static void showTodoDescriptionError() {
        printGenericMessage(ERROR_TODO_NO_DESCRIPTION);
    }

    public static void showDeadlineDescriptionError() {
        printGenericMessage(ERROR_DEADLINE_NO_DESCRIPTION);
    }

    public static void showEventDescriptionError() {
        printGenericMessage(ERROR_EVENT_NO_DESCRIPTION);
    }

    public static void showInvalidCommandError() {
        printGenericMessage(MESSAGE_INVALID_COMMAND);
    }

    public static void showNoTaskNumberError() {
        printGenericMessage(ERROR_NO_TASK_NUMBER_TO_MARK);
    }

    public static void showInvalidTaskNumberError() {
        printGenericMessage(ERROR_INVALID_TASK_NUMBER);
    }

    public static void showInvalidTaskNumberFormatError() {
        printGenericMessage(ERROR_INVALID_TASK_NUMBER_FORMAT);
    }

    public static void showCreateSaveFileError() {
        printGenericMessage(ERROR_CREATING_SAVE_FILE);
    }

    public static void showCreateDirectoryError() {
        printGenericMessage(ERROR_CREATING_DATA_DIRECTORY);
    }

    public static void showConvertSaveFileError() {
        printGenericMessage(ERROR_CONVERTING_SAVE_FILE);
    }

    public static void showReadSaveFileError() {
        printGenericMessage(ERROR_READING_SAVE_FILE);
    }

    public static void showWritingToSaveFileError() {
        printGenericMessage(ERROR_WRITING_TO_SAVE_FILE);
    }

    public static void showUnknownError() {
        printGenericMessage(ERROR_DUKE_UNKNOWN);
    }
}
