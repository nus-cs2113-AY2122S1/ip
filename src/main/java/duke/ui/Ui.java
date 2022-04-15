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
     * Prints the given string in between 2 horizontal lines.
     *
     * @param section The string to be printed.
     */
    private static void printSection(String section) {
        System.out.println(LINE);
        System.out.println(section);
        System.out.println(LINE);
    }

    /**
     * Prints the welcome message as a section.
     */
    public void printWelcomeMessage() {
        printSection(WELCOME_MESSAGE);
    }

    /**
     * Prints the bye message as a section.
     */
    public void printByeMessage() {
        printSection(BYE_MESSAGE);
    }

    /**
     * Prints the header for the list command.
     */
    public void printTaskListHeader() {
        System.out.println("[*] Here are your list of tasks:");
    }

    /**
     * Prints the given list of tasks in a neatly formatted way.
     *
     * @param taskList List of tasks.
     */
    public void printTaskListFormatted(ArrayList<Task> taskList) {
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
     * Prints information about the newly added task.
     *
     * @param taskList List of tasks.
     * @param task     Newly added task.
     */
    public void printTaskListAddMessage(ArrayList<Task> taskList, Task task) {
        System.out.println("[+] Task added:");
        System.out.printf("   [%s][%s] %s\n", task.getTaskIcon(), task.getStatusIcon(), task.getFullDescription());
        System.out.printf("[=] You now have %d tasks in the list.\n", taskList.size());
    }

    /**
     * Prints information about the newly deleted task.
     *
     * @param taskList List of tasks.
     * @param task     Newly deleted task.
     */
    public void printTaskListDeleteMessage(ArrayList<Task> taskList, Task task) {
        System.out.println("[+] Task removed:");
        System.out.printf("   [%s][%s] %s\n", task.getTaskIcon(), task.getStatusIcon(),
                task.getDescription());
        System.out.printf("[=] You now have %d tasks in the list.\n", taskList.size());
    }

    /**
     * Prints information about the newly completed task.
     *
     * @param task Newly completed task.
     */
    public void printTaskListCompleteMessage(Task task) {
        System.out.println("[+] Task marked as done:");
        System.out.printf("   [%s][%s] %s\n", task.getTaskIcon(), task.getStatusIcon(),
                task.getFullDescription());
    }

    /**
     * Prints the header for the find command.
     */
    public void printTaskListFilterHeader() {
        System.out.println("[*] Here are the matching tasks in your list:");
    }

    /**
     * Prints the error when list of tasks is empty.
     */
    public void printTaskListEmptyError() {
        System.out.println("[*] Here are your list of tasks:");
        System.out.println("[X] No tasks found :(");
    }

    /**
     * Prints the error when unable to find task.
     */
    public void printTaskListNotFoundError() {
        System.out.println("[-] Task not found");
    }

    /**
     * Prints the given error message.
     *
     * @param errorMessage Error message.
     */
    public void printError(String errorMessage) {
        System.out.println("[X] " + errorMessage);
    }

    /**
     * Prints the error when converting one data type to another.
     */
    public void printConvertError() {
        printError("Error converting argument!");
    }

    /**
     * Prints the error when updating the file storing the list of tasks.
     */
    public void printFileUpdateError() {
        printError("Error updating save file!");
    }

    /**
     * Prints the error when updating the file storing the list of tasks.
     */
    public void printFileReadError() {
        printError("Error reading save file! Starting from clean slate...");
    }

    /**
     * Returns a line from standard input.
     *
     * @return Line read.
     */
    public String readInput() {
        System.out.printf("[root@%s ~]$ ", BOT_NAME);
        return in.nextLine();
    }
}
