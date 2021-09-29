package duke;

import java.io.IOException;

public class Ui {
    public static void helloWorld() {
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
        printHorizontalLine();
    }


    public static void printHorizontalLine() {
        System.out.println("_".repeat(60));
    }

    public static void displayGeneralFormattingError() {
        System.out.println("OOPS!!! I'm sorry, but I don't know what that means :-(");
    }

    public static void displayDeleteNoError() {
        System.out.println("Oops please give number to delete");
    }

    public static void displayTodoError() {
        System.out.println("☹ OOPS!!! The description of a todo cannot be empty.");
    }

    public static void displayEventError() {
        System.out.println("Oops please enter /at");
    }

    public static void displayDeadlineError() {
        System.out.println("Oops please enter in the format /by ddMMyyyy hhmm");

    }

    public static void displayTaskNoMissing() {
        System.out.println("Oops, please enter which task to be done");
    }

    public static void displayWrongFile() {
        System.out.println("\t☹ Invalid file type in data store.");
    }

    public static void displayNoFile() {
        System.out.println("\t☹ File is not found.");
    }

    public static void displayNoData() {
        System.out.println("\t☹ Directory does not exist, error in loading data store.\n" +
                "Please check if ./data/duke.txt exists.");
    }

    public static void goodBye() throws IOException {
        System.out.println("Bye. Hope to see you again soon!");
        Storage.saveTasks();
        System.out.println();
    }
}
