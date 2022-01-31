import Storage.Storage;
import Task.Task;
import Ui.Ui;
import Parser.Parser;
import java.io.IOException;
import java.util.ArrayList;

public class Duke {

    public static void main(String[] args) {
        Ui.printGreeting();
        ArrayList<Task> tasks = new ArrayList<Task>();
        Parser.parse();

        try {
            Storage.saveToFile(tasks);
        } catch (IOException e) {
            Ui.horizontalLine();
            System.out.println("Oops! Cannot write to file!\n");
        }
        Ui.printBye();
    }
}
