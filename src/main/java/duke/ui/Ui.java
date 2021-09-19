package duke.ui;

import duke.tasklist.Task;

import java.sql.SQLOutput;
import java.util.ArrayList;

/** UI of Duke that deals with interactions with the user */
public class Ui {

    public static final String HORIZONTAL_LINE = "------------------------------------------------------";

    public static final String HELP_LIST =
            "Use the following commands!\n" +
            "To add a task without deadline: to do [taskName]\n" +
            "To add a task with a deadline: deadline [deadlineName] /by [deadline in the format: \"dd MM yyyy hh:mm\"]\n" +
            "To add an event: event [eventName] /at [eventTime in the format: \"dd MM yyyy hh:mm\"]\n" +
            "To mark a task as done: done [taskNumber]\n" +
            "To delete a task from your task list: delete [taskNumber]\n" +
            "To perform a search on your current task list: find [keyword]\n" +
            "To view your current task list, simply type: list\n" +
            "To end your chat with me, simply type: bye\n" +
            HORIZONTAL_LINE;
    public static final String STRING_LOGO =
            " ____        _\n"
            + "|  _ \\ _   _| | _____\n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";
    public static final String INTRODUCTION_MESSAGE =
            "Hello! I'm Duke!\n"
            + "So far, I can create a simple task list for you.\n"
            + "What can I do for you?\n";

    /** Prints introduction message */
    public static void printIntro() {
        System.out.println("Hello from\n" + STRING_LOGO);
        System.out.println(HORIZONTAL_LINE);
        System.out.println(INTRODUCTION_MESSAGE);
    }

    /** Prints exit message */
    public static void printOutro(){
        System.out.println("Bye! Hope to see you again soon!");
        System.out.println(HORIZONTAL_LINE);
    }

    /**
     * Prints message that allows user to know that task has been successfully marked as done
     *
     * @param taskList Task type arraylist to store all the tasks entered by the user
     * @param taskNumber index of task in taskList to be marked as done
     */
    public static void printTaskMarkAsDone(ArrayList<Task> taskList, int taskNumber) {
        Task taskUpdated = taskList.get(taskNumber);
        taskUpdated.updateIsDone();
        taskUpdated.printMarkAsDoneMessage(taskNumber);
    }

    /**
     * Prints task list.
     *
     * @param taskList Task type arraylist to store all the tasks entered by the user
     */
    public static void printTaskList(ArrayList<Task> taskList) {
        int listIndex = 1;
        System.out.println(HORIZONTAL_LINE);
        for (Task task : taskList) {
            task.printTaskList(listIndex);
            listIndex++;
        }
        System.out.println(HORIZONTAL_LINE);
    }

    /**
     * Prints the list of tasks that contains keywords specified by the user
     *
     * @param searchList Task type arraylist to store the list of tasks that contains keywords specified by the user
     */
    public static void printSearchList(ArrayList<Task> searchList) {
        int listIndex = 1;
        for (Task task : searchList) {
            task.printTaskList(listIndex);
            listIndex++;
        }
        System.out.println(HORIZONTAL_LINE);
    }

    /**
     * Checks if the task list has tasks that contain keywords specified by the user
     * If a task is found, this function prints a message to let the user know that the user's search is valid
     *
     * @param isFound returns true if there is at least one task that contains the keyword specified by the user
     */
    public static void printValidSearchMessage(boolean isFound) {
        if (isFound) {
            System.out.println("Search found!");
        } else {
            System.out.println("Task not found in the task list!");
        }
    }

    /**
     * Prints message that allows the user to know that a task has been successfully added to the task list
     *
     * @param taskAdded Task type object to be added to the task list
     */
    public static void printTaskAddedConfirmation(Task taskAdded) {
        System.out.println(HORIZONTAL_LINE);
        taskAdded.printTaskAddedMessage();
        System.out.println(HORIZONTAL_LINE);
    }

    /** Prints help list */
    public static void printHelpList() {
        System.out.println(HELP_LIST);
    }

    /** Prints message that allows the user to know that the command entered is invalid */
    public static void printErrorForInvalidCommand(String userInput) {
        System.out.println("Aw man! I am unable to " + userInput + " yet! Please specify a different function! :D");
    }

    /** Prints message that allows the user to know that a task has been successfully deleted from the task list */
    public static void printTaskDeletedConfirmation(Task taskAdded, int index) {
        System.out.println(HORIZONTAL_LINE);
        taskAdded.printTaskDeletedMessage(index);
        System.out.println(HORIZONTAL_LINE);
    }
}
