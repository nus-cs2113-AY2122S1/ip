import java.util.Scanner;

public class UI {

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
        printMessage( "[+] Welcome to Shell RPG",
                "[+] Searching for Character........",
                "[+] Character " + username + " Found!",
                "[+] Character Level: 100",
                "[+] Access Granted! Welcome to: ",
                " #####  #     # ####### #       #          ######  ######   #####  \n"
                + "#     # #     # #       #       #          #     # #     # #     # \n"
                + "#       #     # #       #       #          #     # #     # #       \n"
                + " #####  ####### #####   #       #          ######  ######  #  #### \n"
                + "      # #     # #       #       #          #   #   #       #     # \n"
                + "#     # #     # #       #       #          #    #  #       #     # \n"
                + " #####  #     # ####### ####### #######    #     # #        #####  "
        );
    }

    /**
     * Prints user prompt
     */
    public void printPrompt() {
        System.out.printf("┌─["
                + "ShellRPG@%s" +
                "]-[~]\n", username);
        System.out.print("└──╼ $ " );
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
