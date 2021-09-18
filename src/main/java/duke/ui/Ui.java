package duke.util;

import duke.task.TaskManager;
import java.util.Scanner;

public class Ui {

    public static final String LINE = "____________________________________________________________";
    public static final String GOODBYE_MESSAGE = "Bye. Hope to see you again soon!";
    public static final String SHELL_RPG_BANNER =
            " #####  #     # ####### #       #          ######  ######   #####  \n"
                    + "#     # #     # #       #       #          #     # #     # #     # \n"
                    + "#       #     # #       #       #          #     # #     # #       \n"
                    + " #####  ####### #####   #       #          ######  ######  #  #### \n"
                    + "      # #     # #       #       #          #   #   #       #     # \n"
                    + "#     # #     # #       #       #          #    #  #       #     # \n"
                    + " #####  #     # ####### ####### #######    #     # #        #####  ";
    public static final String TASKLIST_EMPTY = "There are no tasks in your list";
    public static final String TASKLIST_MESSAGE = "Here are your tasks in your list:";
    private String username;
    private Scanner in;

    public Ui(String username) {
        this.username = username;
        in = new Scanner(System.in);
    }

    /**
     * Reads user input from standard in
     *
     * @return single input from standard in
     */
    public String getUserInput() {
        return in.nextLine();
    }

    public void printCommandHelp() {
        printMessage("Please run one of the following commands:",
                "1. todo <description> - Creates a todo task",
                "2. deadline <description> /by <some deadline> - Creates a deadline task",
                "3. event <description> /at <some day and time> - Creates a event task",
                "4. list - List all tasks",
                "5. done <task number> - Marks a task as completed",
                "6. bye - exits program");
    }

    /**
     * Prints the banner for the chatbot
     */
    public void printBanner() {
        printMessage("[+] Welcome to Shell RPG",
                "[+] Searching for Character........",
                "[+] Character " + username + " Found!",
                "[+] Character Level: 100",
                "[+] Access Granted! Welcome to: ",
                SHELL_RPG_BANNER
        );
    }

    /**
     * Prints user prompt
     */
    public void printPrompt() {
        System.out.printf("┌─["
                + "ShellRPG@%s" +
                "]-[~]\n", username);
        System.out.print("└──╼ $ ");
    }

    /**
     * Prints the terminating message
     */
    public void printGoodbye() {
        printMessage(GOODBYE_MESSAGE);
    }

    /**
     * Prints the message with 2 lines before and after
     *
     * @param messages Array of messages that are input into the function
     */
    public void printMessage(String... messages) {
        System.out.println(LINE);
        for (String message : messages) {
            System.out.println(message);
        }
        System.out.println(LINE);
    }

    /**
     * List all task added by the user Show which task has been completed
     */
    public void printAllTasks(TaskManager taskList) {
        System.out.println(LINE);
        System.out.println(TASKLIST_MESSAGE);
        // Printing all tasks with their completion status
        for (int i = 0; i < taskList.getNumberOfTasks(); i++) {
            System.out.printf("%d. %s\n", (i + 1), taskList.getTask(i));
        }
        System.out.println(LINE);
    }

    /**
     * Shows only if the list of task is empty
     */
    public void printEmptyTaskMessage() {
        printMessage(TASKLIST_EMPTY);
    }
}
