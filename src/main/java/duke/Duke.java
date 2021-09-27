package duke;

import duke.task.Task;

import java.io.FileNotFoundException;
import java.util.ArrayList;

public class Duke {

    public static void main(String[] args) {
        Ui.printWelcomeMessage();
        ArrayList<Task> tasks = new ArrayList<>();
        try {
            tasks = Storage.readFile();
        } catch (FileNotFoundException e) {
            System.out.println("Data file not found");
        }
        int currCount = tasks.size(); // index of next element to be added
        new Parser(tasks, currCount);
        Parser.executeCommands();
        Ui.printGoodBye();
    }
}


