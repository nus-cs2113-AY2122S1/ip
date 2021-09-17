package duke.command;

import duke.datasaver.DataManager;
import java.util.Scanner;

public class Duke {

    // Constants
    public static final String EXIT_STRING = "bye";
    public static final String OUTPUT_DIVIDER = "____________________________________________________________";
    public static final String MESSAGE_GREET_USER = " Hey there! I'm Duke." + System.lineSeparator() + " How may I help you?";
    public static final String MESSAGE_GOODBYE = " Goodbye! Hope to see you again soon.";
    public static final String DUKE_LOGO = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";

    public static void main(String[] args) {
        printLogoAndGreet();

        TaskManager dukeTaskManager = new TaskManager();
        DataManager.loadData(dukeTaskManager.getTaskList());
        String userInput;
        Scanner in = new Scanner(System.in);
        userInput = in.nextLine();

        while (!userInput.trim().equalsIgnoreCase(EXIT_STRING)) {
            dukeTaskManager.handleUserInput(userInput);
            userInput = in.nextLine();
        }

        printGoodbye();
    }

    /**
     * Prints Duke's logo along with a greeting
     */
    public static void printLogoAndGreet() {
        System.out.println("Hello from\n" + DUKE_LOGO);
        System.out.println(OUTPUT_DIVIDER);
        System.out.println(MESSAGE_GREET_USER);
        System.out.println(OUTPUT_DIVIDER);
    }

    /**
     * Prints a goodbye message before exiting the program
     */
    public static void printGoodbye() {
        System.out.println(OUTPUT_DIVIDER);
        System.out.println(MESSAGE_GOODBYE);
        System.out.println(OUTPUT_DIVIDER);
    }
}
