package ui;

import jarvis.Jarvis;
import tasklist.Task;

import java.util.ArrayList;

public class Ui {
    public static final String JARVIS_LOGO = "       __  ___      ____ _    __  ____ _____" + System.lineSeparator()
            + "      / / /   |    / __ \\ |  / / /  _// ___/" + System.lineSeparator()
            + " __  / / / /| |   / /_/ / | / /  / /  \\__ \\ " + System.lineSeparator()
            + "/ /_/ / / ___ |_ / _, _/| |/ / _/ /_ ___/ / " + System.lineSeparator()
            + "\\____(_)_/  |_(_)_/ |_(_)___(_)___(_)____/  " + System.lineSeparator();
    public static final String LINE = "____________________________________________________________";

    /**
     * Prints the logo and greets the user.
     */
    public static void printGreetMessage() {
        System.out.println(JARVIS_LOGO
                + LINE + System.lineSeparator()
                + "Good day Sir! J.A.R.V.I.S. here!" + System.lineSeparator()
                + LINE);
    }


    /**
     * Prints the exit/bye message before closing the app.
     */
    public static void printByeMessage() {
        System.out.println(LINE + System.lineSeparator()
                + "As always Sir, a great pleasure watching you work!" + System.lineSeparator()
                + LINE);
    }

    /**
     * Prints an error message when user inputs an unknown command.
     */
    public static void printIndexOutOfBoundExceptionMessage() {
        System.out.println(LINE + System.lineSeparator()
                + "Apologies Sir, I don't quite get what you mean." + System.lineSeparator()
                + "Try a new command!" + System.lineSeparator()
                + LINE);
    }

    /**
     * Prints an error message when user inputs a command with missing details.
     */
    public static void printInputErrorExceptionMessage() {
        System.out.println(LINE + System.lineSeparator()
                + "Apologies Sir, your input is missing something." + System.lineSeparator()
                + "Try a new command!" + System.lineSeparator()
                + LINE);
    }

    /**
     * Prints an error message when list is empty or when the number input by user
     * is out of range.
     * @param listSize is the current size of the list of tasks
     */
    public static void printInputOutOfRangeExceptionMessage(int listSize) {
        if (listSize == 0) {
            System.out.println(LINE + System.lineSeparator()
                    + "Apologies Sir, your list is currently empty." + System.lineSeparator()
                    + LINE);
        } else {
            System.out.println(LINE + System.lineSeparator()
                    + "Apologies Sir, you picked an out of range number." + System.lineSeparator()
                    + "Please choose a positive integer until " + listSize + "!" + System.lineSeparator()
                    + LINE);
        }
    }

    /**
     * Prints an error message when a deadline input is missing the date & time of deadline.
     */
    public static void printDeadlineExceptionMessage() {
        System.out.println(LINE + System.lineSeparator()
                + "Apologies Sir, you did not mention the deadline." + System.lineSeparator()
                + "Please include the deadline in your command!" + System.lineSeparator()
                + LINE);
    }

    /**
     * Prints an error message when an event input is missing the date & time of the event.
     */
    public static void printEventExceptionMessage() {
        System.out.println(LINE + System.lineSeparator()
                + "Apologies Sir, you did not mention the venue." + System.lineSeparator()
                + "Please include the venue in your command!" + System.lineSeparator()
                + LINE);
    }

    /**
     * Prints a message when a new task has been added to the list along with the task.
     * @param task is the new task that has been added
     * @param taskList main list keeping track of user's tasks
     */
    public static void printAddedTask(Task task, ArrayList<Task> taskList) {
        System.out.println(LINE + System.lineSeparator()
                + "Noted Sir. I've added this task:" + System.lineSeparator()
                + " " + task.toString() + System.lineSeparator()
                + "You currently have " + taskList.size() + " tasks in your list Sir." + System.lineSeparator()
                + LINE);
    }

    /**
     * Prints a message when a task had been marked as done along with the task.
     * @param taskList main list keeping track of user's tasks
     * @param taskNum is the index of the task in the list of task
     */
    public static void printMarkTaskDoneMessage(ArrayList<Task> taskList, int taskNum) {
        System.out.println(LINE + System.lineSeparator()
                + "Good one Sir! I've marked this task as done:" + System.lineSeparator()
                + " " + taskList.get(taskNum-1).toString() + System.lineSeparator()
                + LINE);
    }

    /**
     * Prints a message when a task has been deleted along with the task.
     * @param taskList main list keeping track of user's tasks
     * @param taskNum is the index of the task in the list of task
     */
    public static void printDeleteMessage(ArrayList<Task> taskList, int taskNum) {
        System.out.println(LINE + System.lineSeparator()
                + "Noted Sir! I've removed this task:" + System.lineSeparator()
                + " " + taskList.get(taskNum-1).toString() + System.lineSeparator()
                + "You now have " + (taskList.size()-1) + " tasks in the list Sir!" + System.lineSeparator()
                + LINE);
    }

    /**
     * Prints an error message when input date & time are in the wrong format.
     */
    public static void printDateTimeParseExceptionMessage() {
        System.out.println(LINE + System.lineSeparator()
                + "Apologies Sir, the date & time you entered is in the wrong format." + System.lineSeparator()
                + "Please re-enter the command with the date & time in the correct format [DD/MM/YYYY hhmm]."
                + System.lineSeparator() + LINE);
    }
}
