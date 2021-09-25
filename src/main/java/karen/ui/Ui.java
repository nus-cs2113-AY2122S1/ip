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

    public static String getUserInput() {
        System.out.print(" > ");
        Scanner in = new Scanner(System.in);
        String rawUserInput = in.nextLine().trim();
        return rawUserInput;
    }

    public static void printWelcomeMessage(boolean isFirstRun) {
        System.out.println(KAREN_LOGO);
        if (isFirstRun) {
            printFormattedMessage(WELCOME_MESSAGE);
        } else {
            printFormattedMessage(WELCOME_BACK_MESSAGE);
        }
    }

    public static void printFormattedMessage(String message) {
        System.out.println(LINE + message + "\n" + LINE);
    }

    public static void printTaskAddedMessage(Task task, int totalTasks) {
        String message = String.format("    Okay! I've added this task: \n       [%s][%s] %s\n" +
                "    Now you have %d tasks in your list.\n",
                task.getType(), task.getStatusIcon(), task.getFormattedDescription(), totalTasks);
        printFormattedMessage(message);
    }

    public static void printTaskDeletedMessage(Task task, int totalTasks) {
        String message = String.format("    Okay! I've removed this task: \n       [%s][%s] %s\n" +
                        "    Now you have %d tasks in your list.\n",
                task.getType(), task.getStatusIcon(), task.getFormattedDescription(), totalTasks);
        printFormattedMessage(message);
    }

    public static void printTaskList(ArrayList<Task> tasks) {
        int listSize = tasks.size();

        //empty list
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

    public static void printTaskDoneMessage(Task task) {
        String message = String.format("    Alright! I've marked this task as done!\n    [%s] %s\n",
                task.getStatusIcon(), task.getFormattedDescription());
        printFormattedMessage(message);
    }

    public static void printTaskAlreadyDoneMessage() {
        String message = "    Task is already marked as done!\n";
        printFormattedMessage(message);
    }


    public static void printGoodByeMessage() {
        String message = "    Bye, Plankton. \n";
        printFormattedMessage(message);
    }

    public static void printInvalidCommandMessage() {
        String message = "    Invalid Command. Please try again.\n";
        printFormattedMessage(message);
    }

    public static void printNoDescriptionMessage() {
        String message = "    Empty description! Please specify your command.\n";
        printFormattedMessage(message);
    }

    public static void printIncorrectDescriptionFormatMessage() {
        String message = "    Your Command Description has not been entered properly! Please try again.\n";
        printFormattedMessage(message);
    }

    public static void printNumberFormatMessage() {
        String message = "    You have entered an invalid task number to be completed! Please give a number.\n";
        printFormattedMessage(message);
    }

    public static void printIndexOutOfBoundsMessage() {
        String message = "    You have no such task number. Please enter a number within your list of tasks.\n";
        printFormattedMessage(message);
    }

    public static void printIOExceptionMessage() {
        String message = "    Error when reading or writing to files.\n";
        printFormattedMessage(message);
    }

    public static void printCreateFileErrorMessage() {
        String message = "    Error creating file.\n";
        printFormattedMessage(message);
    }

    public static void printDateTimeErrorMessage() {
        String message = "    Invalid date entered.\n";
        printFormattedMessage(message);
    }

}
