package duke.ui;

import static duke.ui.CommandMessage.ADD_TASK_MESSAGE;
import static duke.ui.CommandMessage.COMPLETE_TASK_MESSAGE;
import static duke.ui.CommandMessage.DELETE_TASK_MESSAGE;
import static duke.ui.CommandMessage.FIND_TASK_MESSAGE;
import static duke.ui.CommandMessage.HELP_MESSAGE;
import static duke.ui.ErrorMessage.NO_TASK_FOUND_ERROR_MESSAGE;

import duke.task.Task;
import java.util.ArrayList;
import java.util.Scanner;

public class Ui {

    /* A nicely formatted line */
    private static final String LINE = "=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=";

    /* Message list */
    private static final String WELCOME_MESSAGE = "Hello! I'm Gaben.\n"
            + "Steam sales is coming! Prepare your wallet.\n"
            + "Anyways, what can I do for you today?";
    private static final String EXIT_MESSAGE = "Thank you for using Gaben.\n"
            + "Remember to keep your wallet stacked before using me again!";


    /* A scanner to read user input */
    private final Scanner in;

    public Ui() {
        this.in = new Scanner(System.in);
    }

    /**
     * Prints given string as given
     *
     * @param message String to be printed
     */
    public void printMessage(String message) {
        System.out.println(message);
    }

    public void printHorizontalLines() {
        System.out.println(LINE);
    }

    public void printWelcomeMessage() {
        printHorizontalLines();
        printMessage(WELCOME_MESSAGE);
        printHorizontalLines();
    }

    public void printExitMessage() {
        printMessage(EXIT_MESSAGE);
    }

    public void printHelpMessage() {
        printMessage(HELP_MESSAGE);
    }

    /**
     * Prints add task message
     *
     * @param task     task that was added
     * @param taskLeft total number of task in tasklist
     */
    public void printAddTaskMessage(Task task, int taskLeft) {
        printMessage(String.format(ADD_TASK_MESSAGE, task.toString(), taskLeft));
    }

    /**
     * Prints complete task message
     *
     * @param task task that was marked as completed
     */
    public void printCompleteTaskMessage(Task task) {
        printMessage(String.format(COMPLETE_TASK_MESSAGE, task.toString()));
    }

    /**
     * Prints list of task message
     *
     * @param taskList Array list of task currently in TaskList class
     */
    public void printListOfTaskMessage(ArrayList<Task> taskList) {
        int taskListSize = taskList.size();
        new StringBuilder();
        StringBuilder message;
        if (taskListSize == 0) {
            message = new StringBuilder("Oh! You have no tasks left!");
        } else {
            message = new StringBuilder("Total of " + taskListSize + " task(s)\n");
            for (Task task : taskList) {
                message.append(String.format("%d.%s\n", task.getTaskNumber(), task));
            }
        }
        printMessage(message.toString());
    }

    /**
     * Prints list of task that fits user search result
     *
     * @param taskList Array list of task that fits user search
     */
    public void printFindTaskMessage(ArrayList<Task> taskList) {
        int taskListSize = taskList.size();
        new StringBuilder();
        StringBuilder message;
        if (taskListSize == 0) {
            message = new StringBuilder(NO_TASK_FOUND_ERROR_MESSAGE);
        } else {
            message = new StringBuilder(String.format(FIND_TASK_MESSAGE, taskListSize));
            for (Task task : taskList) {
                message.append(String.format("%d.%s\n", task.getTaskNumber(), task));
            }
        }
        printMessage(message.toString());
    }

    /**
     * Prints delete task message
     *
     * @param task     task that was deleted
     * @param taskLeft total number of task in tasklist
     */
    public void printDeleteTaskMessage(Task task, int taskLeft) {
        printMessage(String.format(DELETE_TASK_MESSAGE, task.toString(), taskLeft));
    }

    /**
     * Prints error message
     *
     * @param errorMessage error message given by exception caught
     */

    public void printErrorMessage(String errorMessage) {
        printMessage(errorMessage);
    }

    /**
     * Read one line of input of user
     *
     * @return String that was given by user
     */
    public String readCommand() {
        return this.in.nextLine();
    }
}
