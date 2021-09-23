package ui;

import java.io.PrintStream;
import java.util.Scanner;
import static common.Message.MESSAGE_LOGO;
import static common.Message.MESSAGE_WELCOME;

public class TextUI {
    private final Scanner in;
    private final PrintStream out;

    private final static String prefix = "> ";

    public TextUI() {
        in = new Scanner(System.in);
        out = System.out;
        out.println(MESSAGE_LOGO);
        out.println(MESSAGE_WELCOME);
    }

    public String getCommand() {
        out.print(prefix);
        String input = in.nextLine();
        return input;
    }

    public void showMessage(String message) {
        out.println(message);
    }
}
