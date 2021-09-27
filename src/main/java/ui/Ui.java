package ui;

import jarvis.Jarvis;
import tasklist.Task;

import java.util.ArrayList;

public class Ui {
    public static final String JARVIS_LOGO = "       __  ___      ____ _    __  ____ _____\n"
            + "      / / /   |    / __ \\ |  / / /  _// ___/\n"
            + " __  / / / /| |   / /_/ / | / /  / /  \\__ \\ \n"
            + "/ /_/ / / ___ |_ / _, _/| |/ / _/ /_ ___/ / \n"
            + "\\____(_)_/  |_(_)_/ |_(_)___(_)___(_)____/  \n";
    public static final String LINE_W_NL = "____________________________________________________________\n";
    public static final String LINE = "____________________________________________________________";

    /**
     * Prints the logo and greets the user.
     */
    public static void printGreetMessage() {
        System.out.println(JARVIS_LOGO
                + LINE_W_NL
                + "Good day Sir! J.A.R.V.I.S. here!\n"
                + LINE);
    }


    public static void printByeMessage() {
        System.out.println(LINE_W_NL
                + "As always Sir, a great pleasure watching you work!\n"
                + LINE);
    }

    /**
     * Prints an error message when user inputs an unknown command.
     */
    public static void printIndexOutOfBoundExceptionMessage() {
        System.out.println(LINE_W_NL
                + "Apologies Sir, I don't quite get what you mean.\n"
                + "Try a new command!\n"
                + LINE);
    }

    /**
     * Prints an error message when user inputs a command with missing details.
     */
    public static void printNumberFormatExceptionMessage() {
        System.out.println(LINE_W_NL
                + "Apologies Sir, your input is missing something.\n"
                + "Try a new command!\n"
                + LINE);
    }

    /**
     * Prints an error message when list is empty or when the number input by user
     * is out of range.
     * @param listSize is the current size of the list of tasks
     */
    public static void printArithmeticExceptionMessage(int listSize) {
        if (listSize == 0) {
            System.out.println(LINE_W_NL
                    + "Apologies Sir, your list is currently empty.\n"
                    + LINE);
        } else {
            System.out.println(LINE_W_NL
                    + "Apologies Sir, you picked an out of range number.\n"
                    + "Please choose a positive integer until " + listSize + "!\n"
                    + LINE);
        }
    }

    /**
     * Prints an error message when a deadline input is missing the date & time of deadline.
     */
    public static void printDeadlineExceptionMessage() {
        System.out.println(LINE_W_NL
                + "Apologies Sir, you did not mention the deadline.\n"
                + "Please include the deadline in your command!\n"
                + LINE);
    }

    /**
     * Prints an error message when an event input is missing the date & time of the event.
     */
    public static void printEventExceptionMessage() {
        System.out.println(LINE_W_NL
                + "Apologies Sir, you did not mention the venue.\n"
                + "Please include the venue in your command!\n"
                + LINE);
    }

    /**
     * Prints a message when a new task has been added to the list along with the task.
     * @param task is the new task that has been added
     * @param taskList main list keeping track of user's tasks
     */
    public static void printAddedTask(Task task, ArrayList<Task> taskList) {
        System.out.println(LINE_W_NL
                + "Noted Sir. I've added this task:\n"
                + " " + task.toString() + "\n"
                + "You currently have " + taskList.size() + " tasks in your list Sir.\n"
                + LINE);
    }

    /**
     * Prints a message when a task had been marked as done along with the task.
     * @param taskList main list keeping track of user's tasks
     * @param taskNum is the index of the task in the list of task
     */
    public static void printMarkTaskDoneMessage(ArrayList<Task> taskList, int taskNum) {
        System.out.println(LINE_W_NL
                + "Good one Sir! I've marked this task as done:\n"
                + " " + taskList.get(taskNum-1).toString() + "\n"
                + LINE);
    }

    /**
     * Prints a message when a task has been deleted along with the task.
     * @param taskList main list keeping track of user's tasks
     * @param taskNum is the index of the task in the list of task
     */
    public static void printDeleteMessage(ArrayList<Task> taskList, int taskNum) {
        System.out.println(LINE_W_NL
                + "Noted Sir! I've removed this task:\n"
                + " " + taskList.get(taskNum-1).toString() + "\n"
                + "You now have " + (taskList.size()-1) + " tasks in the list Sir!\n"
                + LINE);
    }

    /**
     * Prints an error message when input date & time are in the wrong format.
     */
    public static void printDateTimeParseExceptionMessage() {
        System.out.println(LINE_W_NL
                + "Apologies Sir, the date & time you entered is in the wrong format.\n"
                + "Please re-enter the command with the date & time in the correct format [DD/MM/YYYY hhmm].\n"
                + LINE);
    }
}
