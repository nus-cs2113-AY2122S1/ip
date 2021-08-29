import java.util.Scanner;

public class UI {
    //TODO May require TA's approval for colours
    /**
     * Unicode colours
     */
    private static final String ANSI_RESET = "\u001B[0m";
    private static final String ANSI_BLACK = "\u001B[30m";
    private static final String ANSI_RED = "\u001B[31m";
    private static final String ANSI_GREEN = "\u001B[32m";
    private static final String ANSI_YELLOW = "\u001B[33m";
    private static final String ANSI_BLUE = "\u001B[34m";
    private static final String ANSI_PURPLE = "\u001B[35m";
    private static final String ANSI_CYAN = "\u001B[36m";
    private static final String ANSI_WHITE = "\u001B[37m";

    /**
     * Line template to use for dividers
     */
    private static final String LINE = "____________________________________________________________";
    private String username;
    private Scanner in;

    public UI(String username) {
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
        printMessage(ANSI_YELLOW + "[+] Welcome to Shell RPG",
                "[+] Searching for Character........",
                "[+] Character " + username + " Found!",
                "[+] Character Level: 100",
                "[+] Access Granted! (╯°□°)╯︵ ┻━┻" + ANSI_RESET);
    }

    /**
     * Prints user prompt
     */
    public void printPrompt() {
        System.out.printf(ANSI_RED + "┌─[" + ANSI_RESET
                + ANSI_BLUE + "┻━┻︵ \\(°□°)/ ︵ ┻━┻@%s"
                + ANSI_RESET + ANSI_RED +
                "]-[~]\n" + ANSI_RESET, username);
        System.out.print(ANSI_RED + "└──╼ $ " + ANSI_RESET);
    }

    /**
     * Prints the terminating message
     */
    public void printGoodbye() {
        printMessage("Bye. Hope to see you again soon!");
    }

    /**
     * Prints the message with 2 lines before and after
     *
     * @param messages Array of messages that are input into the function
     */
    public static void printMessage(String... messages) {
        System.out.println(LINE);
        for (String message : messages) {
            System.out.println(message);
        }
        System.out.println(LINE);
    }

    public static void printLine() {
        System.out.println(LINE);
    }
}
