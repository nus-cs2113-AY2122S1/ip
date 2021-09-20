package duke;

import java.sql.SQLSyntaxErrorException;
import java.util.Scanner;

/**
 * This class is in charge of printing required outputs to the console
 */
public class Ui {

    private static final String LOGO = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";
    private static final String LINE_SEPARATOR = "------------------------------------" + System.lineSeparator();
    private static final String HELLO_MESSAGE = "Hello! I'm duke.Duke\nWhat can I do for you?";
    private static final String BYE_MESSAGE = "Bye. Hope to see you again soon!";
    private static final String ERROR_MESSAGE = "You need to specify the task type!";

    protected Scanner in;

    public Ui() {
        in = new Scanner(System.in);
    }

    /**
     * Prints input message with a better style and format to the console
     *
     * @param message the message to be printed
     */
    public void printMessage(String message) {
        String formattedMessage = LINE_SEPARATOR + message + System.lineSeparator() + LINE_SEPARATOR;
        System.out.print(formattedMessage);
    }

    /**
     * Prints greeting message to user when starting the bot
     */
    public void printHelloMessage() {
        System.out.println("Hello from\n" + LOGO);
        printMessage(HELLO_MESSAGE);
    }

    /**
     * Prints farewell message to user when terminating the bot
     */
    public void printByeMessage() {
        printMessage(BYE_MESSAGE);
    }

    /**
     * Prompts the user that the user input is not valid
     */
    public void promptInvalidInput() {
        printMessage(ERROR_MESSAGE);
    }

    /**
     * Prints confirmation when a task is added to the list, together with its details and the total task count
     *
     * @param addedTask the task that is going to be added into the list
     * @param taskCount total number of tasks in the list after the addition
     */
    public void printAddedTask(Task addedTask, int taskCount) {
        String taskAddedConfirmation = "Got it! I've added the following task:";
        String promptTaskCount = "Now you have " + taskCount + " tasks in your list";
        String formattedMessage = taskAddedConfirmation + System.lineSeparator() +
                addedTask.getTaskInfo() +  System.lineSeparator() + promptTaskCount;
        printMessage(formattedMessage);
    }

    /**
     * Prints confirmation when a task is completed, together with its details
     *
     * @param completedTask the task that is being completed
     */
    public void printCompletedTask(Task completedTask) {
        String taskCompletedConfirmation = "Nice! I have marked this task as done:";
        String formattedMessage = taskCompletedConfirmation +
                System.lineSeparator() + completedTask.getTaskInfo();
        printMessage(formattedMessage);
    }

    /**
     * Prints confirmation when a task is removed from the list, together with its details and the total task count
     *
     * @param deletedTask the task that is going to be deleted
     * @param taskCount total number of tasks in the list after the deletion
     */
    public void printDeletedTask(Task deletedTask, int taskCount) {
        String taskDeletedConfirmation = "Noted. I've removed this task:";
        String promptTaskCount = "Now you have " + taskCount + " tasks in your list";
        String formattedMessage = taskDeletedConfirmation + System.lineSeparator() +
                deletedTask.getTaskInfo() + System.lineSeparator() + promptTaskCount;
        printMessage(formattedMessage);
    }

}
