package karen.ui;

import karen.tasklist.task.Task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public abstract class Ui {
    public static final String LINE = "    _____________________________________________________________________________\n\n";
    public static final String LINE_SPACE = "    ";
    public static final String KAREN_ON_LOGO =
            "\n" +
                    "            ______________________\n" +
                    "         _~`o--------------------o: \n" +
                    "        /- ||                    ||\n" +
                    "      -!|. ||          /\\        ||\n" +
                    "     ! ||. |!___ /\\   /  \\  /\\___!|\n" +
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
    public static final String KAREN_OFF_LOGO =
            "            ______________________\n" +
            "         _~`o--------------------o: \n" +
            "        /- ||                    ||\n" +
            "      -!|. ||                    ||\n" +
            "     ! ||. |!____________________!|\n" +
            "     | |!. ||                    ||\n" +
            "      -!!__!|                    ||\n" +
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
            "             0                 0 ";
    public static final String PLANKTON_LOGO =
" )_)  \n" +
        " |0|  \n" +
        "'!_!` \n" +
        " ' '  " ;
    public static final String WELCOME_MESSAGE = "Hello there Plankton, what can I can do for you?\n";

    public static final String WELCOME_BACK_MESSAGE = "Welcome back Plankton, anything else I can do for you?\n";

    /**
     * Returns the user input.
     * This method prompts the user for input, then returns the user input
     * as a String.
     *
     * @return user input that is trimmed with no leading spaces
     */
    public static String getUserInput() {
        System.out.print(PLANKTON_LOGO + " > ");
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
        System.out.println(KAREN_ON_LOGO);
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
        System.out.println(LINE + LINE_SPACE + message + "\n" + LINE);
    }

    /**
     * This method prints a message when a Task object has been added to taskList.
     *
     * @param task Task object to be added into taskList
     * @param totalTasks number of tasks in taskList
     */
    public static void printTaskAddedMessage(Task task, int totalTasks) {
        String message = String.format("Got it. I've added the task: \n" + LINE_SPACE + "   [%s][%s] %s\n" +
                LINE_SPACE + "Now you have %d tasks in your list.\n",
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
        String message = String.format("Alright Plankton, I've removed the task: \n" + LINE_SPACE + "   [%s][%s] %s\n" +
                        LINE_SPACE + "Now you have %d tasks in your list.\n",
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
            printFormattedMessage("You have nothing in your task list so far!\n");
            return;
        }

        String message = String.format("Here you go Plankton \n\n" + LINE_SPACE + "Task List:\n");

        for (int i = 0; i < listSize; i ++) {
           message = message + String.format(LINE_SPACE + "%d. [%s][%s] %s\n",
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
            printFormattedMessage("You seem to have none of such tasks.\n");
            return;
        }
        String message = "Found them. Here are your tasks matching with:  " + keyword + "\n\n";
        for (int i = 0; i < tasks.size(); i ++) {
            message += String.format(LINE_SPACE + "  %d. [%s][%s] %s\n",
                    i+1, tasks.get(i).getType(), tasks.get(i).getStatusIcon(), tasks.get(i).getFormattedDescription());
        }

        message += String.format("\n" + LINE_SPACE + "Total number of tasks: " + tasks.size() + "\n");
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
            printFormattedMessage("You have nothing on that day Plankton\n");
            return;
        }

        String formattedDate = date.format(DateTimeFormatter.ofPattern("MMM d yyyy, E"));
        String message = "Okay Plankton, here are the tasks you have on " + formattedDate + ".\n\n" + LINE_SPACE + "" +
                "Task List:\n\n";

        for (int i = 0; i < tasks.size(); i ++) {
            message += String.format(LINE_SPACE + "  %d. [%s][%s] %s\n",
                    i+1, tasks.get(i).getType(), tasks.get(i).getStatusIcon(), tasks.get(i).getFormattedDescription());
        }

        message += String.format("\n" + LINE_SPACE + "Total number of tasks: " + tasks.size() + "\n");
        printFormattedMessage(message);
    }

    /**
     * This method prints a message when a Task object has been marked as done.
     *
     * @param task Task object to be marked as done
     */
    public static void printTaskDoneMessage(Task task) {
        String message = String.format("Alright, I've marked the task as done!\n" + LINE_SPACE + "[%s] %s\n",
                task.getStatusIcon(), task.getFormattedDescription());
        printFormattedMessage(message);
    }

    /**
     * This method prints a message when user is attempting to mark a Task object that is already marked as done
     * as done.
     */
    public static void printTaskAlreadyDoneMessage() {
        String message = "That Task has already been marked as done Plankton\n";
        printFormattedMessage(message);
    }

    /**
     * This method prints a message when a Bye Command is given and the program has ended.
     */
    public static void printGoodByeMessage() {
        System.out.println(KAREN_OFF_LOGO);
        String message = "See you again, Plankton. \n";
        printFormattedMessage(message);
    }

    /**
     * This method prints a message when an invalid command has been given by user.
     */
    public static void printInvalidCommandMessage() {
        String message = "Come again? I don't quite understand that command Plankton.\n";
        printFormattedMessage(message);
    }

    /**
     * This method prints a message when a command with no description has been given by user.
     */
    public static void printNoDescriptionMessage() {
        String message = "Plankton you need to be a little bit clearer.\n";
        printFormattedMessage(message);
    }

    /**
     * This method prints a message when a command with incorrect formatting of description has been given by user.
     * eg. "done 1 2', "event /at 21-03-2020"
     */
    public static void printIncorrectDescriptionFormatMessage() {
        String message = "What's that? Can you rephrase yourself?\n";
        printFormattedMessage(message);
    }

    /**
     * This method prints a message when a non-number is given in a field where a number is expected.
     */
    public static void printNumberFormatMessage() {
        String message = "I don't think that's even a number. Give a proper task number Plankton.\n";
        printFormattedMessage(message);
    }

    /**
     * This method prints a message when there is no Task object with the index given by user.
     */
    public static void printIndexOutOfBoundsMessage() {
        String message = "You don't have any Tasks with that task number.\n";
        printFormattedMessage(message);
    }

    /**
     * This method prints a message when there are errors when accessing a File.
     */
    public static void printIOExceptionMessage() {
        String message = "Oops, there's an error when I try to access your saved file.\n";
        printFormattedMessage(message);
    }

    /**
     * This method prints a message when there are errors when creating a File.
     */
    public static void printCreateFileErrorMessage() {
        String message = "Oops, there's an error creating a file for you.\n";
        printFormattedMessage(message);
    }

    /**
     * This methods a message when user inputs a date or time incorrectly.
     */
    public static void printDateTimeErrorMessage() {
        String message = "I don't think that's a valid date or time.\n";
        printFormattedMessage(message);
    }

}
