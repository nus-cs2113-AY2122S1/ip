package karen.ui;

import karen.tasklist.task.Task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public abstract class Ui {
    public static final String LINE = "    _____________________________________________________________________________"
            + System.lineSeparator() + System.lineSeparator();
    public static final String LINE_SPACE = "    ";
    public static final String KAREN_ON_LOGO =
            System.lineSeparator() +
                    "            ______________________" + System.lineSeparator() +
                    "         _~`o--------------------o: " + System.lineSeparator() +
                    "        /- ||                    ||" + System.lineSeparator() +
                    "      -!|. ||          /\\        ||" + System.lineSeparator() +
                    "     ! ||. |!___ /\\   /  \\  /\\___!|" + System.lineSeparator() +
                    "     | |!. ||   V  \\ /    \\/     ||" + System.lineSeparator() +
                    "      -!!__!|       V            ||" + System.lineSeparator() +
                    "       `\\. ||____________________||" + System.lineSeparator() +
                    "         `~!o======='---'======= o!" + System.lineSeparator() +
                    "                     | |" + System.lineSeparator() +
                    "                     !_!" + System.lineSeparator() +
                    "                     | |" + System.lineSeparator() +
                    "                     !_!" + System.lineSeparator() +
                    "                    .! !." + System.lineSeparator() +
                    "                   /___'_\\" + System.lineSeparator() +
                    "              ____!----'--!____" + System.lineSeparator() +
                    "             /    |    '  |    \\" + System.lineSeparator() +
                    "            /  - - \\- _'_/ - -  \\      " + System.lineSeparator() +
                    "           /. _________________ .\\" + System.lineSeparator() +
                    "          [(_____________________)]" + System.lineSeparator() +
                    "             0                 0 " + System.lineSeparator();
    public static final String KAREN_OFF_LOGO =
            "            ______________________" + System.lineSeparator() +
            "         _~`o--------------------o: " + System.lineSeparator() +
            "        /- ||                    ||" + System.lineSeparator() +
            "      -!|. ||                    ||" + System.lineSeparator() +
            "     ! ||. |!____________________!|" + System.lineSeparator() +
            "     | |!. ||                    ||" + System.lineSeparator() +
            "      -!!__!|                    ||" + System.lineSeparator() +
            "       `\\. ||____________________||" + System.lineSeparator() +
            "         `~!o======='---'======= o!" + System.lineSeparator() +
            "                     | |" + System.lineSeparator() +
            "                     !_!" + System.lineSeparator() +
            "                     | |" + System.lineSeparator() +
            "                     !_!" + System.lineSeparator() +
            "                    .! !." + System.lineSeparator() +
            "                   /___'_\\" + System.lineSeparator() +
            "              ____!----'--!____" + System.lineSeparator() +
            "             /    |    '  |    \\" + System.lineSeparator() +
            "            /  - - \\- _'_/ - -  \\      " + System.lineSeparator() +
            "           /. _________________ .\\" + System.lineSeparator() +
            "          [(_____________________)]" + System.lineSeparator() +
            "             0                 0 ";
    public static final String PLANKTON_LOGO =
" )_)  " + System.lineSeparator() +
        " |0|  " + System.lineSeparator() +
        "'!_!` " + System.lineSeparator() +
        " ' '  " ;
    public static final String WELCOME_MESSAGE = "Hello there Plankton, what can I can do for you?" + System.lineSeparator();

    public static final String WELCOME_BACK_MESSAGE = "Welcome back Plankton, anything else I can do for you?" + System.lineSeparator();

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
        System.out.println(LINE + LINE_SPACE + message + "" + System.lineSeparator() + LINE);
    }

    /**
     * This method prints a message when a Task object has been added to taskList.
     *
     * @param task Task object to be added into taskList
     * @param totalTasks number of tasks in taskList
     */
    public static void printTaskAddedMessage(Task task, int totalTasks) {
        String message = String.format("Got it. I've added the task: " + System.lineSeparator() + LINE_SPACE + "   [%s][%s] %s" + System.lineSeparator() +
                LINE_SPACE + "Now you have %d tasks in your list." + System.lineSeparator(),
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
        String message = String.format("Alright Plankton, I've removed the task: " + System.lineSeparator() + LINE_SPACE + "   [%s][%s] %s" + System.lineSeparator() +
                        LINE_SPACE + "Now you have %d tasks in your list." + System.lineSeparator(),
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
            printFormattedMessage("You have nothing in your task list so far!" + System.lineSeparator());
            return;
        }

        String message = String.format("Here you go Plankton " + System.lineSeparator() + System.lineSeparator() + LINE_SPACE + "Task List:" + System.lineSeparator());

        for (int i = 0; i < listSize; i ++) {
           message = message + String.format(LINE_SPACE + "%d. [%s][%s] %s" + System.lineSeparator(),
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
            printFormattedMessage("You seem to have none of such tasks." + System.lineSeparator());
            return;
        }
        String message = "Found them. Here are your tasks matching with:  " + keyword + "" + System.lineSeparator() + System.lineSeparator();
        for (int i = 0; i < tasks.size(); i ++) {
            message += String.format(LINE_SPACE + "  %d. [%s][%s] %s" + System.lineSeparator(),
                    i+1, tasks.get(i).getType(), tasks.get(i).getStatusIcon(), tasks.get(i).getFormattedDescription());
        }

        message += String.format("" + System.lineSeparator() + LINE_SPACE + "Total number of tasks: " + tasks.size() + "" + System.lineSeparator());
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
            printFormattedMessage("You have nothing on that day Plankton" + System.lineSeparator());
            return;
        }

        String formattedDate = date.format(DateTimeFormatter.ofPattern("MMM d yyyy, E"));
        String message = "Okay Plankton, here are the tasks you have on " + formattedDate + "." + System.lineSeparator() + System.lineSeparator() + LINE_SPACE + "" +
                "Task List:" + System.lineSeparator() + System.lineSeparator();

        for (int i = 0; i < tasks.size(); i ++) {
            message += String.format(LINE_SPACE + "  %d. [%s][%s] %s" + System.lineSeparator(),
                    i+1, tasks.get(i).getType(), tasks.get(i).getStatusIcon(), tasks.get(i).getFormattedDescription());
        }

        message += String.format("" + System.lineSeparator() + LINE_SPACE + "Total number of tasks: " + tasks.size() + "" + System.lineSeparator());
        printFormattedMessage(message);
    }

    /**
     * This method prints a message when a Task object has been marked as done.
     *
     * @param task Task object to be marked as done
     */
    public static void printTaskDoneMessage(Task task) {
        String message = String.format("Alright, I've marked the task as done!" + System.lineSeparator() + LINE_SPACE + "[%s] %s" + System.lineSeparator(),
                task.getStatusIcon(), task.getFormattedDescription());
        printFormattedMessage(message);
    }

    /**
     * This method prints a message when user is attempting to mark a Task object that is already marked as done
     * as done.
     */
    public static void printTaskAlreadyDoneMessage() {
        String message = "That Task has already been marked as done Plankton" + System.lineSeparator();
        printFormattedMessage(message);
    }

    /**
     * This method prints a message when a Bye Command is given and the program has ended.
     */
    public static void printGoodByeMessage() {
        System.out.println(KAREN_OFF_LOGO);
        String message = "See you again, Plankton. " + System.lineSeparator();
        printFormattedMessage(message);
    }

    /**
     * This method prints a message when an invalid command has been given by user.
     */
    public static void printInvalidCommandMessage() {
        String message = "Come again? I don't quite understand that command Plankton." + System.lineSeparator();
        printFormattedMessage(message);
    }

    /**
     * This method prints a message when a command with no description has been given by user.
     */
    public static void printNoDescriptionMessage() {
        String message = "Plankton you need to be a little bit clearer." + System.lineSeparator();
        printFormattedMessage(message);
    }

    /**
     * This method prints a message when a command with incorrect formatting of description has been given by user.
     * eg. "done 1 2', "event /at 21-03-2020"
     */
    public static void printIncorrectDescriptionFormatMessage() {
        String message = "What's that? Can you rephrase yourself?" + System.lineSeparator();
        printFormattedMessage(message);
    }

    /**
     * This method prints a message when a non-number is given in a field where a number is expected.
     */
    public static void printNumberFormatMessage() {
        String message = "I don't think that's even a number. Give a proper task number Plankton." + System.lineSeparator();
        printFormattedMessage(message);
    }

    /**
     * This method prints a message when there is no Task object with the index given by user.
     */
    public static void printIndexOutOfBoundsMessage() {
        String message = "You don't have any Tasks with that task number." + System.lineSeparator();
        printFormattedMessage(message);
    }

    /**
     * This method prints a message when there are errors when accessing a File.
     */
    public static void printIOExceptionMessage() {
        String message = "Oops, there's an error when I try to access your saved file." + System.lineSeparator();
        printFormattedMessage(message);
    }

    /**
     * This method prints a message when there are errors when creating a File.
     */
    public static void printCreateFileErrorMessage() {
        String message = "Oops, there's an error creating a file for you." + System.lineSeparator();
        printFormattedMessage(message);
    }

    /**
     * This methods a message when user inputs a date or time incorrectly.
     */
    public static void printDateTimeErrorMessage() {
        String message = "I don't think that's a valid date or time." + System.lineSeparator();
        printFormattedMessage(message);
    }

}
