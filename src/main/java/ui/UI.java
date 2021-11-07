package ui;

import task.Task;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Represents the user interface for Duke.
 */
public class UI {
    private static Scanner in = new Scanner(System.in);

    private static void printDividerLine() {
        System.out.println("____________________________________________________________");
    }

    /**
     * Reads and returns the input from the user.
     *
     * @return Input given by the user
     */
    public static String getUserInput() {
        return in.nextLine();
    }

    /**
     * Prints the welcome message.
     */
    public static void printGreetMessage() {
        String logo = " _____    ______      \n"
                + "|_   _|   |  _  \\     \n"
                + "  | | ___ | | | |___  \n"
                + "  | |/ _ \\| | | / _ \\ \n"
                + "  | | (_) | |/ / (_) |\n"
                + "  \\_/\\___/|___/ \\___/ \n";
        System.out.println(logo);
        printDividerLine();
        System.out.println("Hello, I'm ToDo, your personal planner!\n" + "What's on today's agenda?");
        printDividerLine();
    }

    /**
     * Prints the exit message.
     */
    public static void printByeMessage() {
        printDividerLine();
        System.out.println("Bye!");
        printDividerLine();
    }

    /**
     * Prints message after task is added to list.
     *
     * @param tasksList List of tasks
     */
    public static void printAddTaskMessage(ArrayList<Task> tasksList) {
        printDividerLine();
        System.out.println("Got it. I've added this task:");
        System.out.println(tasksList.get(tasksList.size() - 1));
        System.out.println("Now you have " + tasksList.size() + " tasks in the list.");
        printDividerLine();
    }

    /**
     * Prints message for invalid input by the user.
     */
    public static void printUnknownCommandMessage() {
        printDividerLine();
        System.out.println("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
        printDividerLine();
    }

    /**
     * Prints all the tasks in the list.
     *
     * @param tasksList List of tasks
     */
    public static void printTasksList(ArrayList<Task> tasksList) {
        printDividerLine();
        System.out.println("Here are the tasks in your list:");
        int i = 1;
        for (Task item: tasksList) {
            System.out.println((i++) + ". " + item);
        }
        printDividerLine();
    }

    /**
     * Prints error message when user does not give the task description
     * for todo, event, or deadline commands.
     */
    public static void printNoDescriptionFoundMessage() {
        printDividerLine();
        System.out.println("☹ OOPS!!! The description cannot be empty.");
        printDividerLine();
    }

    /**
     * Prints error message when user does not give the
     * entire details for event or deadline commands.
     */
    public static void printInvalidNumberOfParametersMessage() {
        printDividerLine();
        System.out.println("☹ I cannot help you unless you provide the details!");
        printDividerLine();
    }

    /**
     * Prints message after task has been marked as completed.
     *
     * @param description Task description
     */
    public static void printDoneMessage(String description) {
        printDividerLine();
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(description);
        printDividerLine();
    }

    /**
     * Prints error message when user is trying to access task
     * not in list.
     */
    public static void printTaskNotInListMessage() {
        printDividerLine();
        System.out.println("☹ OOPS!!! No such item in the list :-(");
        printDividerLine();
    }

    /**
     * Prints message after task has been deleted from list.
     *
     * @param description Task description
     * @param size Size of ArrayList for tasks
     */
    public static void printDeleteTaskMessage(String description, int size) {
        printDividerLine();
        System.out.println("Got it! I've removed this task:");
        System.out.println(description);
        System.out.println("Now you have " + size + " tasks in the list.");
        printDividerLine();
    }

    /**
     * Prints error message when data is not successfully
     * written to the storage file.
     */
    public static void printFailToWriteMessage() {
        printDividerLine();
        System.out.println("Failed to write data");
        printDividerLine();
    }

    /**
     * Prints error message when storage file not found.
     */
    public static void printFileNotFoundMessage() {
        printDividerLine();
        System.out.println("Error: save file not found");
        printDividerLine();
    }

    /**
     * Prints list of tasks matching the keyword input by the user.
     * If a matching task cannot be found, prints a message.
     *
     * @param tasksList List of matching tasks
     */
    public static void printFindTasksList(ArrayList<Task> tasksList) {
        printDividerLine();
        if (!tasksList.isEmpty()) {
            int i = 1;
            for (Task item : tasksList) {
                System.out.println((i++) + ". " + item);
            }
        } else {
            System.out.println("Could not find matching tasks!");
        }
        printDividerLine();
    }
}
