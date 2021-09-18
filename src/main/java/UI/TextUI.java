package UI;

import java.util.Scanner;

/**
 * Command Line Interface to handle input and output issues.
 * Read the inputs from users and pass it to the back-end server.
 * Get the result from server and print it out to users.
 */
public class TextUI {
    private static final String DIVISIONLINE = "    ____________________________________________________________\n";
    private static final String GREETINGS = "     Hello! I'm Duke\n" + "     Can I get your name?\n";
    private static final String WELCOME = "     Hello! %s\n     What can I do for you?\n";

    private Scanner sc = new Scanner(System.in);

    public String readInput() {
        String input;
        do {
            input = sc.nextLine();
        } while (shouldIgnore(input));
        return input;
    }

    public String getUserName() {
        System.out.println(DIVISIONLINE + GREETINGS + DIVISIONLINE);
        return readInput();
    }

    public void showWelcomeMessage(String userName) {
        System.out.print(DIVISIONLINE);
        System.out.printf(WELCOME, userName);
        System.out.print(DIVISIONLINE);
    }

    public void showError(Exception e) {
        System.out.print(DIVISIONLINE);
        System.out.print(e.toString());
        System.out.print(DIVISIONLINE);
    }

    public void printCommandResult(String result) {
        System.out.print(DIVISIONLINE);
        System.out.print(result);
        System.out.print(DIVISIONLINE);
    }

    private boolean shouldIgnore(String input) {
        if (input.trim().isEmpty()) {
            System.out.print(DIVISIONLINE);
            System.out.print("    Input should not be empty! Please try it again!\n");
            System.out.print(DIVISIONLINE);
            return true;
        }
        return false;
    }

}
