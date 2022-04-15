package ui;

import java.io.IOException;
import java.util.Scanner;

import tasks.Task;
import tasks.TaskList;

/**
 * Text UI of the application
 */
public class TextUi {

    /** A platform independent line separator. */
    public static final String LS = System.lineSeparator();

    /** Offset required to convert between 1-indexing and 0-indexing.  */
    public static final int DISPLAYED_INDEX_OFFSET = 1;

    private static final String DIVIDER = "____________________________________________________________" + LS;
    private static final String LOGO = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";



    private static final String MESSAGE_EMPTY_LIST = "☹ There are currently no tasks in the list";
    private static final String MESSAGE_EXIT = "Bye, see you!";
    private static final String MESSAGE_NEW_FOLDER_CREATED = "New data folder created.";

    private final Scanner in;

    public TextUi() {
        in = new Scanner(System.in);
    }

    /**
     * Reads the text entered by the user
     *
     * @return command (full input) entered by the user.
     */
    public String getUserInput() {
       String input = in.nextLine();
       System.out.println(DIVIDER);
       return input;
    }

    /**
     * Shows a list of tasks to the user, formatted as an indexed list.
     *
     * @param allTasks task list
     */
    public static void showTasksListView(TaskList allTasks) {
        int tasksCount = allTasks.getSize();
        if (tasksCount == 0) {
            System.out.println(MESSAGE_EMPTY_LIST);
        }
        else {
            for(int i = 0; i < tasksCount; i++){
                System.out.println((i + 1) + "." + allTasks.getTask(i));
            }
        }
        System.out.println(DIVIDER);
    }


    /**
     * Generates and prints the welcome message upon the start of the application.
     */
    public static void showWelcomeMessage() {
        System.out.println(DIVIDER
                + LOGO
                + "    Hello! I'm Duke\n"
                + "    How may I assist you today?\n"
                + DIVIDER);
    }

    /** Shows a message when a task has been successfully added to the task list */
    public static void showTaskAddedMessage(Task addedTask, int sizeOfTaskList) {
        System.out.println("Successfully added. Here's the added task: ");
        System.out.println("   " + addedTask);
        if (sizeOfTaskList == 1) {
            System.out.println("There is 1 task in the list now.");
        } else {
            System.out.println("There are " + sizeOfTaskList + " tasks in the list now.");
        }
        System.out.println(DIVIDER);
    }

    /** Shows a message if task to be marked as done has already been done so earlier */
    public static void showTaskAlreadyCompletedMessage() {
        System.out.println("You have already marked this task as done! Time to move on :)");
        System.out.println(DIVIDER);
    }

    /** Shows a message if task successfully marked as done for the first time */
    public static void showTaskMarkedAsDoneMessage(Task completedTask) {
        System.out.println("Awesome! You've completed the following task:");
        System.out.println(" [X] " + completedTask.getDescription());
        System.out.println(DIVIDER);

    }

    /** Shows a message when a task has been successfully deleted from the task list */
    public static void showTaskDeletedMessage(Task deletedTask, int numberOfTasksLeft) {
        System.out.println("Successfully deleted the following task:" + System.lineSeparator() +
                "   " + deletedTask);
        System.out.println("Now you have " + numberOfTasksLeft + " tasks in the list.");
        System.out.println(DIVIDER);
    }

    public static void showTasksFound(TaskList tasksFound, String keyword) {
        int numberOfTasksFound = tasksFound.getSize();
        if (numberOfTasksFound == 0) {
            System.out.printf("There were no Tasks found with the keyword: %s" + LS, keyword);
            System.out.println(DIVIDER);
        } else {
            System.out.println("The following tasks contain the matching keyword:");
            showTasksListView(tasksFound);
        }
    }

    /** Prints the usage info for all available commands */
    public static void showHelpMessage(String usageInfoForAllCommands) {
        System.out.println(usageInfoForAllCommands + LS + DIVIDER);
    }

    public static void showExitMessage() {
        System.out.println(MESSAGE_EXIT + LS + DIVIDER);
    }


    /*
     * ===========================================
     *           ERROR MESSAGES
     * ===========================================
     */

    /** Prints error message when type of command given by user does not match any of the recognized commands */
    public static void showInvalidCommandMessage() {
        System.out.println("☹ Sorry, I did not understand your command.");
        System.out.println(DIVIDER);
    }

    /** Prints error message when the command parameter of an AddDeadline Command is non-empty but in incorrect format */
    public static void showInvalidDeadlineMessage() {
        System.out.println("☹ Invalid Parameters!!! Command parameters should follow: TASK_DESCRIPTION /by DEADLINE");
        System.out.println(DIVIDER);
    }

    /** Prints error message when the command parameter of an AddEvent Command is non-empty but in incorrect format */
    public static void showInvalidEventMessage() {
        System.out.println("☹ Invalid Parameters!!! Command parameters should follow: TASK_DESCRIPTION /at TIME_RANGE");
        System.out.println(DIVIDER);
    }

    /** Prints error message when command parameters of AddToDo, AddDeadline or AddEvent Commands are empty*/
    public static void showEmptyParamMessage(String commandType) {
        System.out.printf("☹ OOPS!!! The description of a %s cannot be empty." + LS, commandType);
        System.out.println(DIVIDER);
    }

    /** Prints error message when AddDeadline or AddEvent Commands are missing one of two command parameters*/
    public static void showMissingTaskDescriptionMessage() {
        System.out.println("☹ OOPS!!! Your task description is missing some key fields.");
        System.out.println(DIVIDER);
    }

    /** Prints error message when user did not specify keyword for Find Command */
    public static void showMissingKeyWordMessage() {
        System.out.println("☹ OOPS!!! You did not enter any keywords for the query.");
        System.out.println(DIVIDER);
    }

    /** Prints error message when Delete or MarkTaskAsDone Commands have invalid command parameters **/
    public static void showInvalidTaskIndexMessage(String commandType) {
        System.out.printf("☹ OOPS!!! \"%s\" command should be followed by Task index." + LS, commandType);
        System.out.println(DIVIDER);
    }

    /**
     * Notifies the user that they do not have any tasks in the list if the task list is empty
     * or Informs them that they are accessing a task index which is not inside the current task list.
     *
     * @param allTasks task list
     */
    public static void showExceedTaskIndexMessage(TaskList allTasks) {
        int tasksCount = allTasks.getSize();
        if (tasksCount == 0) {
            System.out.println("☹ OOPS!!! You do not have any tasks in the list yet!");
        }
        else {
            System.out.println("☹ OOPS!!! The task index you are referring to does not exist in the list!");
        }
        System.out.println(DIVIDER);
    }

    /** Prints error message when data file is accessed incorrectly */
    public static void showIOExceptionMessage(IOException e) {
        System.out.println("Something went wrong: " + e.getMessage());
        System.out.println(DIVIDER);
    }

    /** Prints error message when format of data file input is incorrect */
    public static void showInvalidFileFormatMessage() {
        System.out.println(DIVIDER);
        System.out.println("Data file is corrupted.");
    }

    /** Prints a message notifying the user that a new folder has been created */
    public static void showNewFolderCreatedMessage() {
        System.out.println(DIVIDER);
        System.out.println(MESSAGE_NEW_FOLDER_CREATED);
    }
}
