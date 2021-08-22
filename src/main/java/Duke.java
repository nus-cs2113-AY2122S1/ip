

import java.util.ArrayList;
import java.util.Scanner;

public class Duke {

    /** Line template to use for dividers */
    public static final String DIVIDE = "____________________________________________________________";
    /** Username for the chatbot prompt */
    public static final String USERNAME = "VeryImportantUsername";
    /** Unicode colours */
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_BLACK = "\u001B[30m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_WHITE = "\u001B[37m";

    /** Stores all task added by the user */
    public static ArrayList<String> taskList;
    public static boolean isRunning = true;


    public static void main(String[] args) {

        String userInput;
        Scanner in = new Scanner(System.in);
        taskList = new ArrayList<>();

        // Printing Chatbot Banner
        printMessage(ANSI_YELLOW + "[+] Welcome to Shell RPG Game",
                "[+] Searching for Character........",
                "[+] Character " + USERNAME + " Found!",
                "[+] Character Level: 100",
                "[+] Access Granted! (╯°□°)╯︵ ┻━┻" + ANSI_RESET);

        //Looping to user input from standard in
        while (isRunning) {
            System.out.printf(ANSI_RED + "┌─[" + ANSI_RESET
                    + ANSI_BLUE + "┻━┻︵ \\(°□°)/ ︵ ┻━┻@%s"
                    + ANSI_RESET + ANSI_RED +
                    "]-[~]\n" + ANSI_RESET, USERNAME);
            System.out.print(ANSI_RED + "└──╼ $ " + ANSI_RESET);
            // Reading user input
            userInput = in.nextLine();
            handleCommand(userInput);
        }
        printMessage(ANSI_RED + "Bye. Hope to see you again soon!" + ANSI_RESET);
    }

    /**
     * Handles the user input and decide what actions to run
     *
     * @param command takes in a command from user
     */
    public static void handleCommand(String command) {
        switch (command) {
        case "list":
            listTask();
            break;
        case "bye":
            isRunning = false;
            break;
        default:
            taskList.add(command);
            printMessage(ANSI_GREEN
                    + "Added: "
                    + command
                    + ANSI_RESET);
        }
    }

    /**
     * List all task added by the user
     */
    public static void listTask() {
        System.out.println(DIVIDE);
        for (int i = 0; i < taskList.size(); i++) {
            System.out.println(ANSI_GREEN +
                    (i + 1)
                    + ". "
                    + taskList.get(i)
                    + ANSI_RESET);
        }
        System.out.println(DIVIDE);
    }

    /**
     * Prints the message with 2 lines before and after
     *
     * @param messages Array of messages that are input into the function
     */
    public static void printMessage(String... messages) {
        System.out.println(DIVIDE);
        for (String message : messages) {
            System.out.println(message);
        }
        System.out.println(DIVIDE);
    }
}
