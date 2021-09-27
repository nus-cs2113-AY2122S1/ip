import Storage.Storage;
import Tasks.Task;
import Ui.UserInterface;
import DataAnalysis.DataAnalysis;

import java.io.FileNotFoundException;
import java.util.ArrayList;

public class Duke {
    private Storage storage;
    private UserInterface ui;
    private DataAnalysis dataAnalysis;
    ArrayList<Task> taskList;

    public Duke() throws FileNotFoundException {
        ui = new UserInterface();
        storage = new Storage();
        taskList = storage.loadTasks();
        dataAnalysis = new DataAnalysis();
    }

    public void run() {
        dataAnalysis.listTasks(ui, taskList);
        dataAnalysis.processInput(ui, storage, taskList);
        ui.printFarewell();
    }

    public static void main(String[] args) throws FileNotFoundException {
        new Duke().run();
    }
}

