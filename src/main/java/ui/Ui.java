package ui;

import java.util.Scanner;

public class Ui {

    private static final String ERROR_HEADER = "Error: ";
    private static final String SUCCESS_HEADER = "Success: ";
    private static final String USER_INPUT_HEADER = "duke: $ ";
    private static final String LOGO = " ____        _        \n"
                                    + "|  _ \\ _   _| | _____ \n"
                                    + "| | | | | | | |/ / _ \\\n"
                                    + "| |_| | |_| |   <  __/\n"
                                    + "|____/ \\__,_|_|\\_\\___|\n";
    private static final String GREETINGS = "Hello! I'm Duke\n" +
                                            "What can I do for you?\n";
    private static final String USER_LEAVE_MESSAGE = "Bye. Hope to see you again soon!\n";

    public String getUserInput() {
        System.out.print(USER_INPUT_HEADER);
        Scanner sc = new Scanner(System.in);
        return sc.nextLine();
    }

    public void printGreeting() {
        System.out.println(LOGO);
        System.out.println(GREETINGS);
    }

    public void printBye() {
        System.out.println(USER_LEAVE_MESSAGE);
    }

    public void printSuccessMessage(String successMessage) {
        System.out.println(SUCCESS_HEADER + successMessage);
    }

    public void printErrorMessage(String errorMessage) {
        System.out.println(ERROR_HEADER + errorMessage);
    }

    public void printMessage(String message) {
        System.out.println(message);
    }
}
