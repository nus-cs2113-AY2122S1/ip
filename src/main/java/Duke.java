import java.util.Scanner;

public class Duke {

    /** line use for dividers */
    public static final String DIVIDE = "____________________________________________________________";
    /** Username for the chatbot prompt */
    public static final String USERNAME = "VeryImportantUsername";
    /** Unicode colour */
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_BLACK = "\u001B[30m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_WHITE = "\u001B[37m";


    public static void main(String[] args) {

        String userInput;
        boolean isRunning = true;
        Scanner in = new Scanner(System.in);

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
            if (userInput.equals("bye")) {
                // Terminate program if bye is the input
                isRunning = false;
                userInput = ANSI_RED + "Bye. Hope to see you again soon!";
            } else {
                userInput = ANSI_GREEN + userInput;
            }
            printMessage(userInput + ANSI_RESET);
        }
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
