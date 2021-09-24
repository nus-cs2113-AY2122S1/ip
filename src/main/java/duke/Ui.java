package duke;

import java.util.Scanner;

/**
 * Represents a UI class to deal with user interaction.
 */
public class Ui {
    private static final String WELCOME_MESSAGE = "     Hello! I'm Duke\n   " +
            "  What can I do for you?";
    private static final String EXIT_MESSAGE = "     Bye. Hope to see you" +
            " again soon!";

    private static final String DIVIDER = "    ____________________" +
            "________________________________________";

    /**
     * Prints divider.
     */
    public static void printHorizontalLine() {
        System.out.println(DIVIDER);
    }

    /**
     * Prints welcome message.
     */
    public static void printWelcomeMessage() {
        printHorizontalLine();
        System.out.println(WELCOME_MESSAGE);
        printHorizontalLine();
    }

    /**
     * Prints exit message.
     */
    public static void printExitMessage() {
        printHorizontalLine();
        System.out.println(EXIT_MESSAGE);
        printHorizontalLine();
    }

    /**
     * Prints error message indicating invalid index.
     */
    public void printTaskIndexTooSmallMessage() {
        printHorizontalLine();
        System.out.println("     Please enter a valid task number!");
        printHorizontalLine();
    }

    /**
     * Prints error message indicating index bigger than list size.
     *
     * @param tasks Tasks whose size is to be considered.
     */
    public void printTaskIndexTooBigMessage(TaskList tasks) {
        printHorizontalLine();
        System.out.println("     There is only " + tasks.getTasks().size()
                + " item(s) in the list!");
        printHorizontalLine();
    }

    /**
     * Prints message indicating task is done.
     *
     * @param tasks Tasks to be considered.
     * @param taskIndex Index of the tasks to be considered.
     */
    public void printMarkAsDoneMessage(TaskList tasks, int taskIndex) {
        printHorizontalLine();
        System.out.println("     Nice! I've marked this task as done:");
        System.out.println("       " + tasks.getTasks().get(taskIndex));
        printHorizontalLine();
    }

    /**
     * Prints message indicating task added to list successfully.
     *
     * @param tasks Tasks to be considered.
     */
    public void printAddTaskMessage(TaskList tasks) {
        printHorizontalLine();
        int taskSize = tasks.getTasks().size();
        System.out.println("     Got it. I've added this task:\n"
                + "      " + tasks.getTasks().get(taskSize - 1)
                + "\n     Now you have " + taskSize
                + (taskSize == 1? " task" : " tasks")
                + " in the list.");
        printHorizontalLine();
    }

    /**
     * Prints error message indicating invalid command.
     */
    public void printWrongCommandMessage() {
        printHorizontalLine();
        System.out.println("     ☹ OOPS!!! I'm sorry, but I don't " +
                "know what that means :-(");
        printHorizontalLine();
    }

    /**
     * Prints error message indicating an empty description for <code>Todo</code>.
     */
    public void printEmptyTodoDescriptionMessage() {
        printHorizontalLine();
        System.out.println("     ☹ OOPS!!! The description of " +
                "a todo cannot be empty.");
        printHorizontalLine();
    }

    /**
     * Prints error message indicating a wrong input format for <code>Todo</code>.
     */
    public void printWrongDeadlineFormatMessage() {
        printHorizontalLine();
        System.out.println("     ☹ OOPS!!! Please follow this deadline " +
                "format: {task_description} /by {deadline}");
        printHorizontalLine();
    }

    /**
     * Prints error message indicating an empty description for <code>Deadline</code>.
     */
    public void printEmptyDeadlineDescriptionMessage() {
        printHorizontalLine();
        System.out.println("     ☹ OOPS!!! The description of " +
                "a deadline cannot be empty.");
        printHorizontalLine();
    }

    /**
     * Prints error message indicating a wrong input format for <code>Event</code>.
     */
    public void printWrongEventFormatMessage() {
        printHorizontalLine();
        System.out.println("     ☹ OOPS!!! Please follow this deadline format: " +
                "{event_description} /at {date/time}");
        printHorizontalLine();
    }

    /**
     * Prints error message indicating an empty description for <code>Event</code>.
     */
    public void printEmptyEventDescriptionMessage() {
        printHorizontalLine();
        System.out.println("     ☹ OOPS!!! The description of " +
                "an event cannot be empty.");
        printHorizontalLine();
    }

    /**
     * Prints message to indicate task being deleted successfully.
     *
     * @param tasks Tasks to be considered.
     * @param taskIndex Index of the Tasks to be considered.
     */
    public void printDeleteTaskMessage(TaskList tasks, int taskIndex) {
        int taskSize = tasks.getTasks().size();

        printHorizontalLine();
        System.out.println("     Noted. I've removed this task: ");
        System.out.println("       " + tasks.getTasks().get(taskIndex));
        System.out.println("     Now you have " + (taskSize - 1)
                + (taskSize - 1 <= 1? " task" : " tasks")
                + " in the list.");
        printHorizontalLine();
    }

    /**
     * Prints a task.
     *
     * @param taskNumber Associated number of the task to be printed.
     * @param task Task to be printed.
     */
    public void printTask(int taskNumber, Task task) {
        System.out.println("     " + taskNumber + "."
                + task);
    }

    /**
     * Reads user input.
     *
     * @return User input.
     */
    public static String readCommand() {
        Scanner s = new Scanner(System.in);
        String userInput = s.nextLine();

        return userInput;
    }
}
