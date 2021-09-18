import java.util.Scanner;

public class Duke {

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
