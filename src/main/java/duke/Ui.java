package duke;

import duke.task.Task;

import java.util.ArrayList;
import java.util.Scanner;

public class Ui {

    private Scanner in = new Scanner(System.in);

    public static void printDividerLine() {
        System.out.println("\t_____________________________________________________________________________");
    }

    public void showWelcome() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";

        System.out.println("Hello from\n" + logo);
        printDividerLine();
        System.out.println("\tHello! I'm Duke!\n\tWhat can I do for you?");
        printDividerLine();
        System.out.println("\tHere are the performable actions:");
        System.out.println("\t 1. Add a new To Do by typing \"todo {content of your to do}\".");
        System.out.println("\t 2. Add a new Deadline by typing \"deadline {content of your deadline} /by {date of deadline}\".");
        System.out.println("\t 3. Add a new Event by typing \"event {content of your event} /at {date of event}\".");
        System.out.println("\t 4. Mark a task as done by typing in \"done\" and the index of the task on the list.");
        System.out.println("\t 5. Check all the tasks you have added by typing in \"list\". Done tasks will be marked with an X.");
        System.out.println("\t 6. End the program by typing in \"bye\".");
        printDividerLine();
    }

    public static void showBye() {
        printDividerLine();
        System.out.println("\tBye. Hope to see you again soon!");
        printDividerLine();
    }

    public String readCommand() {
        return in.nextLine();
    }

    public void showLoadingError(Exception e) {
        printDividerLine();
        System.out.println("\t OOPS!!! There was an error: " + e.getMessage() +
                "\n\t Please make sure your input is in the correct format!");
        printDividerLine();
    }

    public void showLoadingError() {
        printDividerLine();
        System.out.println("\t OOPS!!! There was an error, please make sure your input is valid!");
        printDividerLine();
    }

    public void printMarkAsDone(ArrayList<Task> tasks, int indexInteger) {
        printDividerLine();
        System.out.println("\t Nice! I've marked this task as done:");
        System.out.println("\t  " + tasks.get(indexInteger - 1));
        printDividerLine();
    }

    public void printAddedTasked(ArrayList<Task> tasks) {
        printDividerLine();
        System.out.println("\t Got it. I've added this task: ");
        System.out.println("\t   " + tasks.get(Task.getNumberOfTasks() - 1));
        System.out.println("\t Now you have " + Task.getNumberOfTasks() + " tasks in the list.");
        printDividerLine();
    }

    public void listAllTasks(ArrayList<Task> tasks) {
        printDividerLine();
        System.out.println("\t Here are the tasks in your list:");
        for (int i = 0; i < Task.getNumberOfTasks(); i++) {
            System.out.println("      " + (i + 1) + "." + tasks.get(i));
        }
        printDividerLine();
    }

    public void showDeletedTask(String description) {
        printDividerLine();
        System.out.println("\t Noted. I've removed this task:");
        System.out.println("\t  " + description);
        System.out.println("\t Now you have " + Task.getNumberOfTasks() + " tasks in the list.");
        printDividerLine();
    }
}
