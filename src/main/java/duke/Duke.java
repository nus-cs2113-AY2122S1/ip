package duke;

import Command.Command;
import java.util.ArrayList;

/**
 * Enters the Duke application.
 * Initializes the application and starts the interaction with the user.
 */
public class Duke {
    private static final String DATA_FILEPATH = "duke.txt";

    private Ui ui;
    private Storage storage;
    private TaskList tasks;

    /**
     * Instantiates a Duke application.
     */
    public Duke() {
        ui = new Ui();
        storage = new Storage(DATA_FILEPATH);

        ArrayList<Task> savedTasks = storage.getSavedTasks();
        tasks = new TaskList(savedTasks);
    }

    /**
     * Runs the program until user inputs "bye".
     */
    public void run() {
        ui.printWelcomeMessage();

        boolean isExit = false;
        while (!isExit) {
            String fullCommand = ui.readCommand();
            Command c = Parser.parse(fullCommand);
            c.execute(tasks, ui, storage);
            isExit = c.isExit();
        }

        ui.printExitMessage();
    }

    public static void main(String[] args) {
        new Duke().run();
    }
}
