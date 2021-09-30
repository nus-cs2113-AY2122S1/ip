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

    public String getUserInput() {
       String input = in.nextLine();
       System.out.println(DIVIDER);
       return input;
    }

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

    public static void showWelcomeMessage() {
        System.out.println(DIVIDER
                + LOGO
                + "    Hello! I'm Duke\n"
                + "    How may I assist you today?\n"
                + DIVIDER);
    }

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

    public static void showTaskAlreadyCompletedMessage() {
        System.out.println("You have already marked this task as done! Time to move on :)");
        System.out.println(DIVIDER);
    }

    public static void showTaskMarkedAsDoneMessage(Task completedTask) {
        System.out.println("Awesome! You've completed the following task:");
        System.out.println(" [X] " + completedTask.getDescription());
        System.out.println(DIVIDER);

    }

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
        } else {
            System.out.println("The following tasks contain the matching keyword:");
            printEnumeratedTasks(tasksFound, numberOfTasksFound);
        }
        System.out.println(DIVIDER);
    }

    private static void printEnumeratedTasks(TaskList tasksFound, int numberOfTasksFound) {
        for(int i = 0; i < numberOfTasksFound; i++) {
            int displayedIndex = i + DISPLAYED_INDEX_OFFSET;
            System.out.println(displayedIndex + ". " + tasksFound.getTask(i));
        }
    }

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

    public static void showInvalidCommandMessage() {
        System.out.println("☹ Sorry, I did not understand your command.");
        System.out.println(DIVIDER);
    }

    public static void showInvalidDeadlineMessage() {
        System.out.println("☹ Invalid Parameters!!! Command parameters should follow: TASK_DESCRIPTION /by DEADLINE");
        System.out.println(DIVIDER);
    }

    public static void showInvalidEventMessage() {
        System.out.println("☹ Invalid Parameters!!! Command parameters should follow: TASK_DESCRIPTION /at TIME_RANGE");
        System.out.println(DIVIDER);
    }

    public static void showEmptyParamMessage(String commandType) {
        System.out.printf("☹ OOPS!!! The description of a %s cannot be empty." + LS, commandType);
        System.out.println(DIVIDER);
    }

    public static void showMissingTaskDescriptionMessage() {
        System.out.println("☹ OOPS!!! Your task description is missing some key fields.");
        System.out.println(DIVIDER);
    }

    public static void showInvalidTaskIndexMessage(String commandType) {
        System.out.printf("☹ OOPS!!! \"%s\" command should be followed by Task index." + LS, commandType);
        System.out.println(DIVIDER);
    }

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

    public static void showIOExceptionMessage(IOException e) {
        System.out.println("Something went wrong: " + e.getMessage());
        System.out.println(DIVIDER);
    }

    public static void showInvalidFileFormatMessage() {
        System.out.println("Data file is corrupted.");
        System.out.println(DIVIDER);
    }

    public static void showNewFolderCreatedMessage() {
        System.out.println(MESSAGE_NEW_FOLDER_CREATED);
        System.out.println(DIVIDER);
    }
}
