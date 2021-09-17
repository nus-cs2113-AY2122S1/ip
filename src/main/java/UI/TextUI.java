package UI;

import java.util.Scanner;

public class TextUI {
    private static final String DIVISIONLINE = "    ____________________________________________________________\n";
    private static final String GREETINGS = "     Hello! I'm Duke\n" + "     Can I get your name?\n";
    private String WELCOME1 = "     Hello! ";
    private String WELCOME2 = "\n" + "     What can I do for you?\n";

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
        System.out.print(DIVISIONLINE + WELCOME1 + userName + WELCOME2 + DIVISIONLINE);
    }

    public void showError(Exception e) {
        System.out.println(DIVISIONLINE + e.toString() + DIVISIONLINE);
    }

    public void printCommandResult(String result) {
        System.out.println(DIVISIONLINE + result + DIVISIONLINE);
    }

    private boolean shouldIgnore(String input) {
        if (input.trim().isEmpty()) {
            System.out.println(DIVISIONLINE + "    Input should not be empty! Please try it again!\n" + DIVISIONLINE);
            return true;
        }
        return false;
    }

}
