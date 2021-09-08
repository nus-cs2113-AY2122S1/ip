package duke;

import java.util.Scanner;

public class Ui {
    private static final String SEPARATING_LINE = "\t-------------------------------------";
    private static final String GREET_MESSAGE = "Yo what's up I'm Jesse. Need help?";
    private static final String BYE_MESSAGE = "So long!";

    Scanner scanner = new Scanner(System.in);

    public void print(String msg) {
        System.out.println(SEPARATING_LINE);
        System.out.println("\t" + msg);
        System.out.println(SEPARATING_LINE);
    }

    public String readCommand() {
        return scanner.nextLine();
    }

    public void greet() {
        print(GREET_MESSAGE);
    }

    public void bye() {
        print(BYE_MESSAGE);
    }

    public Ui() {

    }

}
