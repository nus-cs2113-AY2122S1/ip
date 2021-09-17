package duke.ui;

import java.util.ArrayList;
import java.util.Scanner;
import duke.task.Task;

/**
 * The Ui class deals with printing of messages and reading of user input.
 */
public class Ui {

    private final static String LINE = "____________________________________________________________";

    /* Name of chat-bot */
    private final static String BOT_NAME = "taskmon";

    /* List of chat-bot messages */
    private final static String WELCOME_MESSAGE = ""
            + "[*] Detecting chat-bot version...\n"
            + "[+] Chat-bot version is *VULNERABLE*!\n"
            + "[*] Hacking into the chat-bot...\n"
            + "[*] Escalating privileges...\n"
            + "[+] Interactive shell spawned. You can now manipulate the chat-bot directly!";
    private final static String BYE_MESSAGE = "[*] Deleting traces of compromise...\n"
            + "[+] Bye. Hope to see you again soon!";

    /* Used to read input from user */
    private Scanner in;

    public Ui() {
        in = new Scanner(System.in);
    }

    /**
     * Print the given string in between 2 horizontal lines.
     *
     * @param section The string to be printed.
     */
    private static void printSection(String section) {
        System.out.println(LINE);
        System.out.println(section);
        System.out.println(LINE);
    }

    /**
     * Print the welcome message as a section.
     */
    public static void printWelcomeMessage() {
        printSection(WELCOME_MESSAGE);
    }

    /**
     * Print the bye message as a section.
     */
    public static void printByeMessage() {
        printSection(BYE_MESSAGE);
    }

    /**
     * Print the header for the list command.
     */
    public static void printTaskListHeader() {
        System.out.println("[*] Here are your list of tasks:");
    }

    /**
     * Print the given list of tasks in a neatly formatted way.
     *
     * @param taskList List of tasks.
     */
    public static void printTaskListFormatted(ArrayList<Task> taskList) {
        Task task;
        String output = "";
        for (int i = 0; i < taskList.size(); i++) {
            task = taskList.get(i);
            output += String.format("   %d.[%s][%s] %s\n", i + 1, task.getTaskIcon(), task.getStatusIcon(),
                    task.getFullDescription());
        }
        System.out.print(output);
    }

    /**
     * Print information about the newly added task.
     *
     * @param taskList List of tasks.
     * @param task     Newly added task.
     */
    public static void printTaskListAddMessage(ArrayList<Task> taskList, Task task) {
        System.out.println("[+] Task added:");
        System.out.printf("   [%s][%s] %s\n", task.getTaskIcon(), task.getStatusIcon(), task.getFullDescription());
        System.out.printf("[=] You now have %d tasks in the list.\n", taskList.size());
    }

    /**
     * Print information about the newly deleted task.
     *
     * @param taskList List of tasks.
     * @param task     Newly deleted task.
     */
    public static void printTaskListDeleteMessage(ArrayList<Task> taskList, Task task) {
        System.out.println("[+] Task removed:");
        System.out.printf("   [%s][%s] %s\n", task.getTaskIcon(), task.getStatusIcon(),
                task.getDescription());
        System.out.printf("[=] You now have %d tasks in the list.\n", taskList.size());
    }

    /**
     * Print information about the newly completed task.
     *
     * @param task Newly completed task.
     */
    public static void printTaskListCompleteMessage(Task task) {
        System.out.println("[+] Task marked as done:");
        System.out.printf("   [%s][%s] %s\n", task.getTaskIcon(), task.getStatusIcon(),
                task.getFullDescription());
    }

    /**
     * Print the header for the find command.
     */
    public static void printTaskListFilterHeader() {
        System.out.println("[*] Here are the matching tasks in your list:");
    }

    /**
     * Print the error when list of tasks is empty.
     */
    public static void printTaskListEmptyError() {
        System.out.println("[*] Here are your list of tasks:");
        System.out.println("[X] No tasks found :(");
    }

    /**
     * Print the error when unable to find task.
     */
    public static void printTaskListNotFoundError() {
        System.out.println("[-] Task not found");
    }

    /**
     * Print the given error message.
     *
     * @param errorMessage Error message.
     */
    public static void printError(String errorMessage) {
        System.out.println("[X] " + errorMessage);
    }

    /**
     * Print the error when converting one data type to another.
     */
    public static void printConvertError() {
        printError("Error converting argument!");
    }

    /**
     * Print the error when updating the file storing the list of tasks.
     */
    public static void printFileUpdateError() {
        printError("Error updating save file!");
    }

    /**
     * Print the error when updating the file storing the list of tasks.
     */
    public static void printFileReadError() {
        printError("Error reading save file! Starting from clean slate...");
    }

    /**
     * Return a line from standard input.
     *
     * @return Line read.
     */
    public String readInput() {
        System.out.printf("[root@%s ~]$ ", BOT_NAME);
        return in.nextLine();
    }
}
