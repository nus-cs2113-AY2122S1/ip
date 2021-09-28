import java.io.IOException;
import java.util.Scanner;

public class Duke {

    public static final String DIVIDER_LINE = "\t__________________________________________________";

    /**
     * Prints the divider line before and after each printed message.
     */
    public static void printLine() {
        System.out.println(DIVIDER_LINE);
    }

    /**
     * Prints the greeting message at the start when the program runs.
     */
    public static void Greet() {
        printLine();
        System.out.println("\tHello! I'm Duke, your friendly agenda chatbot!\n"
                + "\tIs there anything I can do for you today?");
        printLine();
    }

    /**
     * Prints the goodbye message right before the program ends.
     */
    public static void Bye() {
        printLine();
        System.out.println("\tBye. Have a productive day!");
        printLine();
    }

    /**
     * Prints the Duke logo.
     */
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
        Storage.initialiseFiles();
        Storage.startupScanFileContents();
        Scanner in = Ui.getScanner();
        String line = Ui.getLine(in);
        String[] words = Ui.getWords(line);
        Parser.programLogic(in, line, words);
        Bye();
    }

}
