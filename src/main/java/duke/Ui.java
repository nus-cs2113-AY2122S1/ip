package duke;

import duke.tasks.Task;

import java.sql.SQLOutput;
import java.util.Scanner;

public class Ui {
    public static final String LOGO = " _____         _____\n"
            + "|     \\ _____ |     \\ _____\n"
            + "|  o  /|     ||  o  /|     |\n"
            + "|  o  \\|  o  ||  o  \\|  o  |\n"
            + "|_____/|_____||_____/|_____|\n";
    public static final String HORIZONTAL_LINE = "____________________________________________________________";

    public void showLine() {
        System.out.println(HORIZONTAL_LINE);
    }

    public void showHello() {
        System.out.println(LOGO);
        showLine();
        System.out.println("Hello! I'm Bobo!");
        System.out.println("I'm a little blur, but I'd love to help!");
        showLine();
    }

    public void showBye() {
        System.out.println("Ok. Bye bye!");
    }

    public void showError(String errorMessage) {
        System.out.println(errorMessage);
    }

    public String readCommand() {
        Scanner in = new Scanner(System.in);
        return in.nextLine();
    }

    //Ui for Storage
    public void showLoadingError() {
        System.out.println("Error: Seems like directory/file does not exist");
        System.out.println("Creating new file...");
    }

    //Ui for DoneCommand
    public void showDone(int taskIndex, TaskList tasks) {
        System.out.println("Okie! Marked this as done:");
        System.out.println((taskIndex + 1) + "." + tasks.getTask(taskIndex));
    }

    public void showAlreadyDone() {
        System.out.println("This task is already done!");
    }

    public void showInvalidTaskIndexError() {
        System.out.println("Oh no! There isn't a task with that index");
        System.out.println("Please try again!");
    }

    public void showMissingDoneIndexError() {
        System.out.println("Oh no! An integer must come after the done command!");
        System.out.println("Please try again!");
    }

    //Ui for Tasks
    public void showTaskConfirmation(TaskList tasks) {
        int numberOfTasks = tasks.getTasks().size();
        System.out.println("Umm ok added:");
        System.out.println("  " + tasks.getTask(numberOfTasks - 1));
        System.out.println("Now you have " + numberOfTasks + " tasks in the list.");
    }

    //Ui for DeadlineCommand
    public void showMissingByError() {
        System.out.println("Oh no! Deadline tasks must be followed by /by keyword!");
        System.out.println("Please try again!");
    }

    //Ui for EventCommand
    public void showMissingAtError() {
        System.out.println("Oh no! Event tasks must be followed by /at keyword!");
        System.out.println("Please try again!");
    }

    //Ui for DeleteCommand
    public void showInvalidDeleteIndexError() {
        System.out.println("Oh no! There isn't a task with that index");
        System.out.println("Please try again!");
    }

    public void showMissingDeleteIndexError() {
        System.out.println("Oh no! An integer must come after the delete command!");
        System.out.println("Please try again!");
    }

    public void showDeleteError() {
        System.out.println("Error: Unable to delete!");
        System.out.println("Seems like the file does not exist!");
    }

    public void showDeleteConfirmation(Task deletedTask, TaskList tasks) {
        System.out.println("Okie! Deleted this task:");
        System.out.println("  " + deletedTask);
        System.out.println("Now you have " + tasks.getTasks().size() + " tasks in the list.");
    }
}
