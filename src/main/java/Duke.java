import Storage.Storage;
import Tasks.Task;
import Ui.UserInterface;
import DataAnalysis.DataAnalysis;

import java.io.FileNotFoundException;
import java.util.ArrayList;

/**
 * Main class
 */
public class Duke {
    private Storage storage;
    private UserInterface ui;
    private DataAnalysis dataAnalysis;
    ArrayList<Task> taskList;

    /**
     * Initializes User Interface, loads stored task data, and initializes data analysis class
     */
    public Duke() throws FileNotFoundException {
        ui = new UserInterface();
        storage = new Storage();
        taskList = storage.loadTasks();
        dataAnalysis = new DataAnalysis();
    }

    /**
     * Runs program by listing stored tasks, analyzing input data until program ends
     */
    public void run() {
        dataAnalysis.listTasks(ui, taskList);
        dataAnalysis.processInput(ui, storage, taskList);
        ui.printFarewell();
    }

    /**
     * Initializes Duke and runs it
     */
    public static void main(String[] args) throws FileNotFoundException {
        new Duke().run();
    }
}

