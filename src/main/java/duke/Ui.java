package duke;

import duke.tasks.Task;

import java.sql.SQLOutput;
import java.util.Scanner;

/**
 * Ui class that contains all the methods that write to the output.
 */
public class Ui {
    public static final String LOGO = " _____         _____\n"
            + "|     \\ _____ |     \\ _____\n"
            + "|  o  /|     ||  o  /|     |\n"
            + "|  o  \\|  o  ||  o  \\|  o  |\n"
            + "|_____/|_____||_____/|_____|\n";
    public static final String HORIZONTAL_LINE = "____________________________________________________________";

    /**
     * Prints a horizontal line separator.
     */
    public void showLine() {
        System.out.println(HORIZONTAL_LINE);
    }

    /**
     * Prints the logo and hello message.
     */
    public void showHello() {
        System.out.println(LOGO);
        showLine();
        System.out.println("Hello! I'm Bobo!");
        System.out.println("I'm a little blur, but I'd love to help!");
        showLine();
    }

    /**
     * Prints the exit message.
     */
    public void showBye() {
        System.out.println("Ok. Bye bye!");
    }

    /**
     * Prints the error message.
     * @param errorMessage error message to be shown to user
     */
    public void showError(String errorMessage) {
        System.out.println(errorMessage);
    }

    /**
     * Reads the command input by the user and returns it as a string.
     * @return the command input by the user
     */
    public String readCommand() {
        Scanner in = new Scanner(System.in);
        return in.nextLine();
    }

    //Ui for Storage

    /**
     * Prints the error message for when the specified file is not available.
     */
    public void showLoadingError() {
        System.out.println("Error: Seems like directory/file does not exist");
        System.out.println("Creating new file...");
    }

    //Ui for DoneCommand

    /**
     * Prints the confirmation message that a task has been marked as done.
     * @param taskIndex index of task marked as done
     * @param tasks TaskList object containing all the tasks
     */
    public void showDone(int taskIndex, TaskList tasks) {
        System.out.println("Okie! Marked this as done:");
        System.out.println((taskIndex + 1) + "." + tasks.getTask(taskIndex));
    }

    /**
     * Prints a message alerting the user that a task requested to be marked as done is already done.
     */
    public void showAlreadyDone() {
        System.out.println("This task is already done!");
    }

    /**
     * Prints error message when user inputs an illegal task number to be marked as done.
     */
    public void showInvalidTaskIndexError() {
        System.out.println("Oh no! There isn't a task with that index");
        System.out.println("Please try again!");
    }

    /**
     * Prints error message when user fails to input a task number with the done command.
     */
    public void showMissingDoneIndexError() {
        System.out.println("Oh no! An integer must come after the done command!");
        System.out.println("Please try again!");
    }

    //Ui for Tasks

    /**
     * Prints confirmation that a specified task has been added to the list.
     * @param tasks TaskList object containing all tasks in an ArrayList
     */
    public void showTaskConfirmation(TaskList tasks) {
        int numberOfTasks = tasks.getTasks().size();
        System.out.println("Umm ok added:");
        System.out.println("  " + tasks.getTask(numberOfTasks - 1));
        System.out.println("Now you have " + numberOfTasks + " tasks in the list.");
    }

    //Ui for DeadlineCommand

    /**
     * Prints error message when user fails to input '/by' following a deadline command.
     */
    public void showMissingByError() {
        System.out.println("Oh no! Deadline tasks must be followed by /by keyword!");
        System.out.println("Please try again!");
    }

    //Ui for EventCommand

    /**
     * Prints error message when user fails to input '/at' following a event command.
     */
    public void showMissingAtError() {
        System.out.println("Oh no! Event tasks must be followed by /at keyword!");
        System.out.println("Please try again!");
    }

    //Ui for DeleteCommand

    /**
     * Prints error message when illegal task number to be deleted is input by the user.
     */
    public void showInvalidDeleteIndexError() {
        System.out.println("Oh no! There isn't a task with that index");
        System.out.println("Please try again!");
    }

    /**
     * Prints error message when user fails to input a task number following the delete command.
     */
    public void showMissingDeleteIndexError() {
        System.out.println("Oh no! An integer must come after the delete command!");
        System.out.println("Please try again!");
    }

    /**
     * Prints error message when the programme is unable to delete the task due to the file not being found.
     */
    public void showDeleteError() {
        System.out.println("Error: Unable to delete!");
        System.out.println("Seems like the file does not exist!");
    }

    /**
     * Prints confirmation that a specified task has been deleted.
     * @param deletedTask task that has been deleted
     * @param tasks TaskList object containing all tasks as a ArrayList
     */
    public void showDeleteConfirmation(Task deletedTask, TaskList tasks) {
        System.out.println("Okie! Deleted this task:");
        System.out.println("  " + deletedTask);
        System.out.println("Now you have " + tasks.getTasks().size() + " tasks in the list.");
    }
}
