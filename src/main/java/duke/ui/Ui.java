package duke.ui;

import duke.task.Task;

import java.util.ArrayList;
import java.util.List;

public class Ui {

    private static final String HORIZONTAL_LINE = "____________________________________________________________\n";
    private static final String MARK_TASKS_USAGE_MESSAGE = HORIZONTAL_LINE +
            "Please provide me with a valid task to mark as done/not done!\n" +
            "Some usage examples:\n" +
            "-> Done 1, 2, 3\n" +
            "-> Done 1 2 3\n" +
            HORIZONTAL_LINE;
    private static final String GOODBYE_MESSAGE = " Bye. Hope to see you again soon!\n";
    private static final String GREETING_MESSAGE = HORIZONTAL_LINE +
            "Hello! I'm Billy\n" +
            HORIZONTAL_LINE;
    private static final String LOGO = " ______    _   __   __            \n" +
            "|_   _ \\  (_) [  | [  |           \n" +
            "  | |_) | __   | |  | |   _   __  \n" +
            "  |  __'.[  |  | |  | |  [ \\ [  ] \n" +
            " _| |__) || |  | |  | |   \\ '/ /  \n" +
            "|_______/[___][___][___][\\_:  /   \n" +
            "                         \\__.'    \n";
    private static final String HELP_MESSAGE = "Here's some tips on how to use me!\n\n" +
            "todo <DESCRIPTION>\n" +
            "\t - Add a todo task to the list\n\n" +
            "deadline <DESCRIPTION> /by <DATE> [TIME]\n" +
            "\t - Add a deadline task by the given deadline\n" +
            "\t - Date is in the format dd/MM/yyyy and Time is in the format HH:mm\n\n" +
            "event [input] /at <DATE> [TIME]\n" +
            "\t - Add an event task at the given time\n" +
            "\t - Date is in the format dd/MM/yyyy and Time is in the format HH:mm\n\n" +
            "list\n" +
            "\t - List out all the current tasks\n\n" +
            "done <TASK_ID>\n" +
            "\t - Marks the given tasks as done\n\n" +
            "find <DESCRIPTION>\n" +
            "\t - Finds a task based on the given task DESCRIPTION\n" +
            "delete <TASK_ID>\n\n" +
            "\t - Deletes the given tasks\n\n" +
            "bye\n" +
            "\t - Terminates me :(\n";

    public static String getHorizontalLine() {
        return HORIZONTAL_LINE;
    }

    public static void printList(List<Task> list) {
        Task task;
        System.out.println(HORIZONTAL_LINE + "Here are the tasks in your list:");
        for (int i = 0; i < list.size(); i++) {
            task = list.get(i);
            System.out.println(i + 1 + "." + task);
        }
        System.out.print(HORIZONTAL_LINE);
    }

    public static void printGreetingMessage() {
        System.out.print("Hello from\n" + LOGO + GREETING_MESSAGE);
    }

    public static void printGoodbyeMessage() {
        System.out.print(HORIZONTAL_LINE + GOODBYE_MESSAGE + HORIZONTAL_LINE);
    }

    public static void printHelpMessage() {
        System.out.print(HORIZONTAL_LINE + HELP_MESSAGE + HORIZONTAL_LINE);
    }

    public static void printTaskAddedMessage(Task task) {
        System.out.print(HORIZONTAL_LINE + "I've added this task:\n" +
                task + System.lineSeparator() + HORIZONTAL_LINE);
    }

    public static void printDeleteAttemptStartMessage() {
        System.out.print(Ui.HORIZONTAL_LINE + "Awesome! I shall now try to delete the following...\n");
    }

    public static void printDeleteAttemptEndMessage() {
        System.out.print("Boom, insect. The tasks are now DELETED!\n" + Ui.HORIZONTAL_LINE);
    }

    public static void printDeletedTaskMessage(Task task) {
        System.out.println("Deleted: " + task);
    }

    public static void printFoundTasksMessage(ArrayList<Task> list) {
        Task task;
        System.out.print(HORIZONTAL_LINE + "Here are the matching tasks I've found:\n");
        for (int i = 0; i < list.size(); i++) {
            task = list.get(i);
            System.out.println(i + 1 + "." + task);
        }
        System.out.print(HORIZONTAL_LINE);
    }
}
