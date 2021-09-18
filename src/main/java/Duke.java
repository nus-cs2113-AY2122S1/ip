import java.util.Scanner;

/**
 * Duke class acts as the main class of the program.
 */
public class Duke {

    /**
     * Entry point of program upon startup.
     *
     * @param args User input argument.
     */
    public static void main(String[] args) {
        Storage.setupStorage();
        Ui.greet();

        String input;
        do {
            Scanner scan = new Scanner(System.in);
            input = scan.nextLine();

            Parser.parseInput(input);

        } while (!input.startsWith("bye"));

    }
}
