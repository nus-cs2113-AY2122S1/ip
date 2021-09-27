package duke;

import duke.Ui.Ui;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.task.Task;

import java.util.ArrayList;

public class Duke {

    public static void main(String[] args) {
        ArrayList<Task> tasks = new ArrayList<Task>();
        Storage.loadData(tasks);
        Ui.printHello();
        Parser.task();
        Ui.printBye();
    }
}

