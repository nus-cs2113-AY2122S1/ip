package duke;

import duke.task.Task;
import java.util.ArrayList;
import java.io.IOException;

public class Duke {
    private static final String PATH = "data/duke.txt";
    private static Ui ui;
    private static Storage storage;
    private static Parser parser;

    /**
     * Constructor class for Duke program.
     * Initializes a UI and Storage class for the program.
     */
    public Duke() {
        ui = new Ui();
        storage = new Storage();
    }

    /**
     * Takes in user input repeatedly.
     * Terminates when a user inputs <b>bye</b>.
     * @throws IOException
     */
    public static void run() throws IOException {
        ArrayList<Task> tasksArrayList = new ArrayList<>();
        ui.showWelcomeMessage();
        tasksArrayList = storage.loadData();
        parser.executeProgramWithErrorHandlings(tasksArrayList);
        ui.exitProgram();
    }

    public static void main(String[] args) throws IOException {
        new Duke().run();
    }

}
