package Duke;

import java.util.Scanner;

public class UserInterface {
    Scanner line = new Scanner(System.in);
    public static final String BORDER = "_________________________________________\n";
    public static final String UNKNOWN_COMMAND_MESSAGE = "I'm sorry, I didn't understand.\n";
    public static final String NOT_A_NUMBER_MESSAGE = "The input was not a number. Please Try Again\n";
    public static final String NOT_ENOUGH_PARAMS_MESSAGE = "The input had insufficient parameters. "
            + "Please Try Again. \nFor command syntax, typ: help\n";
    private static final String GREETING = "****************************\n"
            + "*  ____             ____   *\n"
            + "* |  _ \\    ____   |  _ \\  *\n"
            + "* | |_| |  / __ \\  | |_| | *\n"
            + "* |  _  | | |  | | |  _  | *\n"
            + "* | |_| | | |__| | | |_| | *\n"
            + "* |____/   \\____/  |____/  *\n"
            + "****************************\n"
            + BORDER
            + "Have no fear, Bob is here!\n"
            + "What is it that you require?\n"
            + "For a list of commands, type: help\n"
            + BORDER;

    public static final String EXIT_MESSAGE = BORDER + "Bye. Have a nice day!\n" + BORDER;

    public UserInterface() {
        Scanner line = new Scanner(System.in);
    }

    public void printInsufficientParameters() {
        System.out.println(BORDER + NOT_ENOUGH_PARAMS_MESSAGE + BORDER);
    }

    public void printGreeting() {
        System.out.println(GREETING);
    }

    public void printGoodbye() {
        System.out.println(EXIT_MESSAGE);
    }

    public String getUserInput() {
        return line.nextLine().trim();
    }

    public void printInfo(String infoForUser) {
        System.out.println(BORDER + infoForUser + BORDER);
    }

    public void printUnknownCommand() {
        System.out.println(BORDER + UNKNOWN_COMMAND_MESSAGE + BORDER);
    }

    public void printNotNumber() {
        System.out.println(BORDER + NOT_A_NUMBER_MESSAGE + BORDER);
    }
}
