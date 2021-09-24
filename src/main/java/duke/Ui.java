package duke;

import java.util.Scanner;

public class Ui {
    private static final String WELCOME_MESSAGE = "     Hello! I'm Duke\n   " +
            "  What can I do for you?";
    private static final String EXIT_MESSAGE = "     Bye. Hope to see you" +
            " again soon!";

    private static final String DIVIDER = "    ____________________" +
            "________________________________________";

    public static void printHorizontalLine() {
        System.out.println(DIVIDER);
    }

    public static void printWelcomeMessage() {
        printHorizontalLine();
        System.out.println(WELCOME_MESSAGE);
        printHorizontalLine();
    }

    public static void printExitMessage() {
        printHorizontalLine();
        System.out.println(EXIT_MESSAGE);
        printHorizontalLine();
    }

    public void printTaskIndexTooSmallMessage() {
        printHorizontalLine();
        System.out.println("     Please enter a valid task number!");
        printHorizontalLine();
    }

    public void printTaskIndexTooBigMessage(TaskList tasks) {
        printHorizontalLine();
        System.out.println("     There is only " + tasks.getTasks().size()
                + " item(s) in the list!");
        printHorizontalLine();
    }

    public void printMarkAsDoneMessage(TaskList tasks, int taskIndex) {
        printHorizontalLine();
        System.out.println("     Nice! I've marked this task as done:");
        System.out.println("       " + tasks.getTasks().get(taskIndex));
        printHorizontalLine();
    }

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

    public void printWrongCommandMessage() {
        printHorizontalLine();
        System.out.println("     ☹ OOPS!!! I'm sorry, but I don't " +
                "know what that means :-(");
        printHorizontalLine();
    }

    public void printEmptyTodoDescriptionMessage() {
        printHorizontalLine();
        System.out.println("     ☹ OOPS!!! The description of " +
                "a todo cannot be empty.");
        printHorizontalLine();
    }

    public void printWrongDeadlineFormatMessage() {
        printHorizontalLine();
        System.out.println("     ☹ OOPS!!! Please follow this deadline " +
                "format: {task_description} /by {deadline}");
        printHorizontalLine();
    }

    public void printEmptyDeadlineDescriptionMessage() {
        printHorizontalLine();
        System.out.println("     ☹ OOPS!!! The description of " +
                "a deadline cannot be empty.");
        printHorizontalLine();
    }

    public void printWrongEventFormatMessage() {
        printHorizontalLine();
        System.out.println("     ☹ OOPS!!! Please follow this deadline format: " +
                "{event_description} /at {date/time}");
        printHorizontalLine();
    }

    public void printEmptyEventDescriptionMessage() {
        printHorizontalLine();
        System.out.println("     ☹ OOPS!!! The description of " +
                "an event cannot be empty.");
        printHorizontalLine();
    }

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

    public void printTask(int taskNumber, Task task) {
        System.out.println("     " + taskNumber + "."
                + task);
    }

    public static String readCommand() {
        Scanner s = new Scanner(System.in);
        String userInput = s.nextLine();

        return userInput;
    }
}
