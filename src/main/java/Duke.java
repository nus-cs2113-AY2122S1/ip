import java.io.IOException;
import java.util.Scanner;

public class Duke {

    public static final String DIVIDER_LINE = "\t__________________________________________________";

    public static void printLine() {
        System.out.println(DIVIDER_LINE);
    }

    public static void Greet() {
        printLine();
        System.out.println("\tHello! I'm Duke, your friendly agenda chatbot!\n"
                + "\tIs there anything I can do for you today?");
        printLine();
    }

    public static void Bye() {
        printLine();
        System.out.println("\tBye. Have a productive day!");
        printLine();
    }

    private static void DukeLogo() {
        String logo = " ____        _        " + System.lineSeparator()
                + "|  _ \\ _   _| | _____ " + System.lineSeparator()
                + "| | | | | | | |/ / _ \\" + System.lineSeparator()
                + "| |_| | |_| |   <  __/" + System.lineSeparator()
                + "|____/ \\__,_|_|\\_\\___|";
        System.out.println("Hello from\n" + logo);
    }

    public static void main(String[] args) throws IOException {
        DukeLogo();
        Greet();
        Storage.initialise();
        Storage.startupScanFileContents();
        Scanner in = Ui.getScanner();
        String line = Ui.getLine(in);
        String[] words = Ui.getWords(line);
        Parser.programLogic(in, line, words);
        Bye();
    }

}
