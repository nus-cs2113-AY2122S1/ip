package duke.ui;

import duke.tasklist.TaskList;
import duke.tasklist.task.Task;
import java.util.Scanner;

public class Ui {
    private static final String logo =
            " _____  ___  _____     ______\n"
                    + "|___  | | | |_____|  / / -- \\ \\ \n"
                    + "   / /  | |    / /  | |      | | \n"
                    + "  / /   | |   / /   | |      | |\n"
                    + " / /___ | |  /_/__  | |  --  | |\n"
                    + "|_____| | | |_____|  \\ \\____/ /\n";
    public static final String border = "____________________________________________________________\n";

    /**
     * Check and create save folder and save file if it does not exist
     *
     */
    public void showLine() {
        System.out.println(border);
    }
    public void showError() {
        System.out.println("Error occurred! Please try again.");
    }
    public void showSaveFileError() {
        System.out.println("Could not find existing save file!");
    }
    public void printStartMessage() {
        System.out.println(logo);
        System.out.println(border + "Hi bro, my name is Zizo");
        System.out.println("What do you want?\n" + border);
        System.out.println("Type bye to exit\n" + border);
    }
    public void printEndMessage() {
        System.out.println(border);
        System.out.println("chat again next time!\n" + border);
    }
    public void addTaskMessage(TaskList tasks, Task task) {
        int taskCount = tasks.getTaskCount();
        System.out.println(border);
        System.out.println("Got it. I've added this task:");
        System.out.println(task);
        System.out.println("Now you have " + taskCount + " tasks in the list.");
        System.out.println(border);
    }
    public void removeTaskMessage(Task task, int taskCount) {
        System.out.println(border);
        System.out.println("Noted. I've removed this task:");
        System.out.println(task);
        System.out.println("Now you have " + taskCount + " tasks in the list.");
        System.out.println(border);
    }
    public void printEmptyDescriptionError(String command) {
        System.out.println(border);
        System.out.println("\uD83D\uDE00 " + "OOPS!!! The description of a " + command + " cannot be empty.");
        System.out.println(border);
    }
    public void printCommandDoesNotExist() {
        System.out.println(border);
        System.out.println("\uD83D\uDE00 " + "OOPS!!! I'm sorry, but I don't know what that means :-(");
        System.out.println(border);
    }
    public void printCommandIsInvalid() {
        System.out.println(border);
        System.out.println("\uD83D\uDE00 " + "OOPS!!! I'm sorry, the command is invalid.");
        System.out.println(border);
    }
    public void printEmptyDateError() {
        System.out.println(border);
        System.out.println("\uD83D\uDE00 " + "OOPS!!! I'm sorry, please input the date you would like to search for " +
                           "in yyyy/mm/dd format" );
        System.out.println(border);
    }
    public void printFindFieldEmpty() {
        System.out.println(border);
        System.out.println("\uD83D\uDE00 " + "OOPS!!! I'm sorry, please input a keyword for the task " +
                "you would like to search for");
        System.out.println(border);
    }
    public void printWrongDateFormatError() {
        System.out.println(border);
        System.out.println("\uD83D\uDE00 " + "OOPS!!! I'm sorry, please input the correct date format: " +
                        "yyyy/dd/mm");
        System.out.println(border);
    }
    public String readCommand(Scanner in) {
        return in.nextLine();
    }
    public Ui() {
        printStartMessage();
    }
}
