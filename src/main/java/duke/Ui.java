package duke;

import java.util.Scanner;

/**
 * User interface, all inputs and outputs come out from here
 *
 * @author {fansxx}-reused
 * {Some Ui usage. with minor modifications}
 */
public class Ui {

    public static final String LINE_SEPARATOR = "____________________________________________________________";

    /**
     * Print welcome message
     */
    public static void showWelcome() {
        String logo = " ____        _        \n"
                    + "|  _ \\ _   _| | _____ \n"
                    + "| | | | | | | |/ / _ \\\n"
                    + "| |_| | |_| |   <  __/\n"
                    + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println(logo);
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
        System.out.println(LINE_SEPARATOR);
    }

    /**
     * Print bye message before exit
     */
    public static void showBye() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    /**
     * Print a line
     */
    public static void showLine() {
        System.out.println(LINE_SEPARATOR);
    }

    /**
     *
     * @return get user input
     */
    public static String readCommand() {
        Scanner sc = new Scanner(System.in);
        return sc.nextLine();
    }

    /**
     * Print error message
     * @param meg obtained from DukeException message
     */
    public static void showError(String meg) {
        System.out.println(meg);
    }

    /**
     * Print task is done message
     */
    public static void showDone() {
        System.out.println("Nice! I've marked this task as done: ");
    }

    /**
     * Print task added message
     */
    public static void printAddedTaskMessage() {
        System.out.println("Got it. I've added this task: ");
    }

    /**
     * Print number of tasks
     * @param todo_index number of tasks
     */
    public static void printTaskNumberMessage(int todo_index) {
        System.out.println("Now you have " + todo_index + " tasks in the list.");
    }

    /**
     * Print delete task message
     */
    public static void printDeletedTaskMessage() {
        System.out.println("Noted. I've removed this task: ");
    }

    /**
     * Print find stored file message
     */
    public static void fileAlreadyExist() {
        System.out.println("Detected a previous saved file.");
    }

    /**
     * Print find task message
     */
    public static void printFindTaskMessage() {
        System.out.println("Here are the matching tasks in your list:");
    }
}
