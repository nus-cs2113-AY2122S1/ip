package karen.ui;

import karen.tasklist.task.Task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public abstract class Ui {
    public static final String LINE = "    _____________________________________________________________________________\n\n";
    public static final String KAREN_LOGO =
            "\n" +
                    "            ______________________\n" +
                    "         _~`o--------------------o: \n" +
                    "        /- ||                    ||\n" +
                    "      -!|. ||          /\\        ||\n" +
                    "     ! ||. ||___ /\\   /  \\  /\\___||\n" +
                    "     | |!. ||   V  \\ /    \\/     ||\n" +
                    "      -!!__!|       V            ||\n" +
                    "       `\\. ||____________________||\n" +
                    "         `~!o======='---'======= o!\n" +
                    "                     | |\n" +
                    "                     !_!\n" +
                    "                     | |\n" +
                    "                     !_!\n" +
                    "                    .! !.\n" +
                    "                   /___'_\\\n" +
                    "              ____!----'--!____\n" +
                    "             /    |    '  |    \\\n" +
                    "            /  - - \\- _'_/ - -  \\      \n" +
                    "           /. _________________ .\\\n" +
                    "          [(_____________________)]\n" +
                    "             0                 0 \n" ;
    public static final String WELCOME_MESSAGE = "    Hello there Plankton, what can I can do for you?\n";

    public static final String WELCOME_BACK_MESSAGE = "    Welcome back Plankton, anything else I can do for you?\n";

    /**
     * Returns the user input.
     * This method prompts the user for input, then returns the user input
     * as a String.
     *
     * @return user input that is trimmed with no leading spaces
     */
    public static String getUserInput() {
        System.out.print(" > ");
        Scanner in = new Scanner(System.in);
        String rawUserInput = in.nextLine().trim();
        return rawUserInput;
    }

    /**
     * This methods prints a welcome message when the program starts.
     *
     * @param isFirstRun boolean to check if the program is running for the first time
     */
    public static void printStartMessage(boolean isFirstRun) {
        System.out.println(KAREN_LOGO);
        if (isFirstRun) {
            printFormattedMessage(WELCOME_MESSAGE);
        } else {
            printFormattedMessage(WELCOME_BACK_MESSAGE);
        }
    }

    /**
     * This method prints the given message formatted between two lines.
     *
     * @param message String to be printed out
     */
    public static void printFormattedMessage(String message) {
        System.out.println(LINE + message + "\n" + LINE);
    }

    /**
     * This method prints a message when a Task object has been added to taskList.
     *
     * @param task Task object to be added into taskList
     * @param totalTasks number of tasks in taskList
     */
    public static void printTaskAddedMessage(Task task, int totalTasks) {
        String message = String.format("    Okay! I've added this task: \n       [%s][%s] %s\n" +
                "    Now you have %d tasks in your list.\n",
                task.getType(), task.getStatusIcon(), task.getFormattedDescription(), totalTasks);
        printFormattedMessage(message);
    }

    /**
     * This method prints a message when a Task object has been removed from taskList.
     *
     * @param task Task object to be added into taskList
     * @param totalTasks number of tasks in taskList
     */
    public static void printTaskDeletedMessage(Task task, int totalTasks) {
        String message = String.format("    Okay! I've removed this task: \n       [%s][%s] %s\n" +
                        "    Now you have %d tasks in your list.\n",
                task.getType(), task.getStatusIcon(), task.getFormattedDescription(), totalTasks);
        printFormattedMessage(message);
    }


    /**
     * This method prints the list of Task objects in taskList, with their formatted description.
     *
     * @param tasks list of the Task objects
     */
    public static void printTaskList(ArrayList<Task> tasks) {
        int listSize = tasks.size();

        if (listSize == 0) {
            printFormattedMessage("    Your List is still Empty!\n");
            return;
        }

        String message = String.format("    Alright! Here is your list of tasks! \n\n    Task List:\n");

        for (int i = 0; i < listSize; i ++) {
           message = message + String.format("    %d. [%s][%s] %s\n",
                   i + 1, tasks.get(i).getType(), tasks.get(i).getStatusIcon(),
                   tasks.get(i).getFormattedDescription());
        }
        printFormattedMessage(message);
    }

    /**
     * This method prints the list of Task objects in taskList that matches with the query keyword
     * used in Find Command.
     *
     * @param tasks list of the Task objects
     * @param keyword query keyword given by user to find tasks with matching description as the keyword
     */
    public static void printFoundTasks(List<Task> tasks, String keyword) {
        if (tasks.size() == 0) {
            printFormattedMessage("    There seems to have no matching tasks\n");
            return;
        }
        String message = "    Here are your tasks matching with:  " + keyword + "\n\n";
        for (int i = 0; i < tasks.size(); i ++) {
            message += String.format("      %d. [%s][%s] %s\n",
                    i+1, tasks.get(i).getType(), tasks.get(i).getStatusIcon(), tasks.get(i).getFormattedDescription());
        }

        message += String.format("\n    Total number of tasks: " + tasks.size() + "\n");
        printFormattedMessage(message);
    }

    /**
     * This method prints the list of Task objects in taskList that occurs on the same date as the
     * date given by user.
     *
     * @param date LocalDate given by user for finding Tasks occurring on that date
     * @param tasks list of the Task objects
     */
    public static void printTasksOnDate (LocalDate date, List<Task> tasks) {
        if (tasks.size() == 0) {
            printFormattedMessage("    You are free on that day!\n");
            return;
        }

        String formattedDate = date.format(DateTimeFormatter.ofPattern("MMM d yyyy, E"));
        String message = "    Okay Plankton, here are the tasks you have on " + formattedDate + ".\n\n     Task List:\n\n";

        for (int i = 0; i < tasks.size(); i ++) {
            message += String.format("      %d. [%s][%s] %s\n",
                    i+1, tasks.get(i).getType(), tasks.get(i).getStatusIcon(), tasks.get(i).getFormattedDescription());
        }

        message += String.format("\n    Total number of tasks: " + tasks.size() + "\n");
        printFormattedMessage(message);
    }

    /**
     * This method prints a message when a Task object has been marked as done.
     *
     * @param task Task object to be marked as done
     */
    public static void printTaskDoneMessage(Task task) {
        String message = String.format("    Alright! I've marked this task as done!\n    [%s] %s\n",
                task.getStatusIcon(), task.getFormattedDescription());
        printFormattedMessage(message);
    }

    /**
     * This method prints a message when user is attempting to mark a Task object that is already marked as done
     * as done.
     */
    public static void printTaskAlreadyDoneMessage() {
        String message = "    Task is already marked as done!\n";
        printFormattedMessage(message);
    }

    /**
     * This method prints a message when a Bye Command is given and the program has ended.
     */
    public static void printGoodByeMessage() {
        String message = "    Bye, Plankton. \n";
        printFormattedMessage(message);
    }

    /**
     * This method prints a message when an invalid command has been given by user.
     */
    public static void printInvalidCommandMessage() {
        String message = "    Invalid Command. Please try again.\n";
        printFormattedMessage(message);
    }

    /**
     * This method prints a message when a command with no description has been given by user.
     */
    public static void printNoDescriptionMessage() {
        String message = "    Empty description! Please specify your command.\n";
        printFormattedMessage(message);
    }

    /**
     * This method prints a message when a command with incorrect formatting of description has been given by user.
     */
    public static void printIncorrectDescriptionFormatMessage() {
        String message = "    Your Command Description has not been entered properly! Please try again.\n";
        printFormattedMessage(message);
    }

    /**
     * This method prints a message when a non-number is given in a field where a number is expected.
     */
    public static void printNumberFormatMessage() {
        String message = "    You have entered an invalid task number to be completed! Please give a number.\n";
        printFormattedMessage(message);
    }

    /**
     * This method prints a message when there is no Task object with the index given by user.
     */
    public static void printIndexOutOfBoundsMessage() {
        String message = "    You have no such task number. Please enter a number within your list of tasks.\n";
        printFormattedMessage(message);
    }

    /**
     * This method prints a message when there are errors when accessing a File.
     */
    public static void printIOExceptionMessage() {
        String message = "    Error when reading or writing to files.\n";
        printFormattedMessage(message);
    }

    /**
     * This method prints a message when there are errors when creating a File.
     */
    public static void printCreateFileErrorMessage() {
        String message = "    Error creating file.\n";
        printFormattedMessage(message);
    }

    /**
     * This methods a message when user inputs a date or time incorrectly.
     */
    public static void printDateTimeErrorMessage() {
        String message = "    Invalid date entered.\n";
        printFormattedMessage(message);
    }

}
