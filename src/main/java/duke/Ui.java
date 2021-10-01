package duke;

import duke.task.Task;

import java.util.ArrayList;
import java.util.Scanner;


/**
 * Text UI of the application that deals with interactions with the user.
 */
public class Ui {

    private Scanner in = new Scanner(System.in);

    /**
     * Prints the divider line.
     */
    public static void printDividerLine() {
        System.out.println("\t_____________________________________________________________________________");
    }

    /**
     * Prints the welcome message at the start of the application.
     */
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
        System.out.println("\t 6. Delete a task by typing in \"delete\" and the index of the task on the list.");
        System.out.println("\t 7. Find a task with a particular keyword by typing in \"find {keyword}\". " +
                "Tasks with that keyword will be listed.");
        System.out.println("\t 8. Show this list of performable actions again by typing \"help\".");
        System.out.println("\t 9. End the program by typing in \"bye\".");
        printDividerLine();
    }

    public void showHelp() {
        printDividerLine();
        System.out.println("\tHere are the performable actions:");
        System.out.println("\t 1. Add a new To Do by typing \"todo {content of your to do}\".");
        System.out.println("\t 2. Add a new Deadline by typing \"deadline {content of your deadline} /by {date of deadline}\".");
        System.out.println("\t 3. Add a new Event by typing \"event {content of your event} /at {date of event}\".");
        System.out.println("\t 4. Mark a task as done by typing in \"done\" and the index of the task on the list.");
        System.out.println("\t 5. Check all the tasks you have added by typing in \"list\". Done tasks will be marked with an X.");
        System.out.println("\t 6. Delete a task by typing in \"delete\" and the index of the task on the list.");
        System.out.println("\t 7. Find a task with a particular keyword by typing in \"find {keyword}\". " +
                "Tasks with that keyword will be listed.");
        System.out.println("\t 8. Show this list of performable actions again by typing \"help\".");
        System.out.println("\t 9. End the program by typing in \"bye\".");
        printDividerLine();
    }

    /**
     * Prints the bye message at the end of a session of the application.
     */
    public static void showBye() {
        printDividerLine();
        System.out.println("\tBye. Hope to see you again soon!");
        printDividerLine();
    }

    /**
     * Reads in and returns the command entered in by the user in the terminal.
     *
     * @return The command entered by the user.
     */
    public String readCommand() {
        return in.nextLine();
    }

    /**
     * Prints the specific error message of an exception.
     *
     * @param e Exception to the error.
     */
    public void showLoadingError(Exception e) {
        printDividerLine();
        System.out.println("\t OOPS!!! There was an error: " + e.getMessage() +
                "\n\t Please make sure your input is in the correct format!");
        printDividerLine();
    }

    /**
     * Prints a general error message.
     */
    public void showLoadingError() {
        printDividerLine();
        System.out.println("\t OOPS!!! There was an error, please make sure your input is valid!");
        printDividerLine();
    }

    /**
     * Prints the task that is marked as done to the user.
     *
     * @param tasks List of all the tasks.
     * @param indexInteger Index of the task that is marked as done by the user.
     */
    public void printMarkAsDone(ArrayList<Task> tasks, int indexInteger) {
        printDividerLine();
        System.out.println("\t Nice! I've marked this task as done:");
        System.out.println("\t  " + tasks.get(indexInteger - 1));
        printDividerLine();
    }

    /**
     * Prints the task that is added by the user.
     *
     * @param tasks List of all the tasks.
     * @param numberOfTasks Total number of tasks.
     */
    public void printAddedTasked(ArrayList<Task> tasks, int numberOfTasks) {
        printDividerLine();
        System.out.println("\t Got it. I've added this task: ");
        System.out.println("\t   " + tasks.get(numberOfTasks - 1));
        System.out.println("\t Now you have " + numberOfTasks + " tasks in the list.");
        printDividerLine();
    }

    /**
     * Prints the entire list of tasks.
     *
     * @param tasks List of all the tasks.
     * @param numberOfTasks Total number of tasks.
     */
    public void listAllTasks(ArrayList<Task> tasks, int numberOfTasks) {
        printDividerLine();
        System.out.println("\t Here are the tasks in your list:");
        for (int i = 0; i < numberOfTasks; i++) {
            System.out.println("      " + (i + 1) + "." + tasks.get(i));
        }
        printDividerLine();
    }

    /**
     * Prints the task that is deleted by the user.
     *
     * @param description Description field of the task.
     * @param numberOfTasks Total number of tasks.
     */
    public void showDeletedTask(String description, int numberOfTasks) {
        printDividerLine();
        System.out.println("\t Noted. I've removed this task:");
        System.out.println("\t  " + description);
        System.out.println("\t Now you have " + numberOfTasks + " tasks in the list.");
        printDividerLine();
    }

    /**
     * Prints the list of tasks with the keyword specified by the user in the find command.
     *
     * @param filteredList The list of tasks with the keyword specified by the user.
     */
    public void listMatchingTasks(ArrayList<Task> filteredList) {
        printDividerLine();
        System.out.println("\t Here are the matching tasks in your list:");
        for (int i = 0; i < filteredList.size(); i++) {
            System.out.println("      " + (i + 1) + "." + filteredList.get(i));
        }
        printDividerLine();
    }
}
