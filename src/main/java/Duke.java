import task.Task;
import java.util.ArrayList;

public class Duke {

    public static void main(String[] args) {
        Ui.printGreeting();
        ArrayList<Task> tasks = Storage.loadFile();
        ChatBot.run(tasks);
        Ui.printFarewell();
    }

}