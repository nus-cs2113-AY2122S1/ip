import Storage.Storage;
import Task.Task;
import Ui.Ui;
import Parser.Parser;
import java.io.IOException;
import java.util.ArrayList;
import java.lang.*;

public class Duke {

    public static void main(String[] args) {
        Ui.printGreeting();
        ArrayList<Task> tasks = new ArrayList<Task>();
        try {
            Storage.writeToFile(tasks);
        } catch (IOException e) {
            Ui.horizontalLine();
            System.out.println("Oops! File not found!\n");
        }
        Parser.parse();
        Ui.printBye();
    }
}
