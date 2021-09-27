import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        TaskList list = new TaskList();
        Storage.SetupFile(list);

        Ui.printGreeting();
        String line;
        Scanner in = new Scanner(System.in);
        boolean dukeRunning = true;

        while (dukeRunning) {
            line = in.nextLine();
            dukeRunning = Parser.parse(list, line);
        }
        Ui.printBye();
    }
}