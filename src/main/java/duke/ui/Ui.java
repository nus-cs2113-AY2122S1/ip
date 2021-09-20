package duke.ui;

import duke.text.Text;
import java.util.Scanner;

public class Ui extends Text {

    private final Scanner in;

    public Ui() {
        this.in = new Scanner(System.in);
    }


    public String getUserCommand() {
        System.out.print(GET_COMMAND);
        return in.nextLine();
    }

    public static void printWithLine(String message) {
        System.out.print(LINE + message + LINE);
    }

    public void printIntroduction() {
        printWithLine(LOGO);
        printWithLine(HELLO_MESSAGE);
    }

    public static void printHelp() {
        printWithLine(HELP_LIST);
    }

    public static void printBye() {
        printWithLine(GOODBYE_MESSAGE);
    }
}
