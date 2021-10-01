package duke.ui;

import java.util.Scanner;

public class Ui {
    private static final String HORIZONTAL_LINE = "____________________________________________________________";
    private static final Scanner SCANNER = new Scanner(System.in);
    public static final String SAD_FACE = "\uD83D\uDE41";

    public Ui(){
    }

    public void printLogo() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
    }

    public static void printGreeting(String s, String s2) {
        System.out.println(HORIZONTAL_LINE);
        System.out.println(s);
        System.out.println(s2);
    }

    public static String getUserInput() {
        System.out.println(HORIZONTAL_LINE);
        System.out.println("Enter command: ");
        String input = SCANNER.nextLine();
        while (input.trim().isEmpty()) {
            input = SCANNER.nextLine();
        }
        return input;
    }


    public static void printInvalidInput() {
        System.out.println(HORIZONTAL_LINE);
        System.out.println(SAD_FACE + " OOPS! I'm sorry, but I don't know what that means " + SAD_FACE);
    }

    public static void exitProgram() {
        printGreeting("Bye. Hope to see you again soon!", HORIZONTAL_LINE);
        System.exit(0);
    }
}
