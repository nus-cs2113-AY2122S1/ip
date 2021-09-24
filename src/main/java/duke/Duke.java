package duke;

import Command.Command;
import java.util.ArrayList;

public class Duke {
    private static final String DATA_FILEPATH = "duke.txt";

    private Ui ui;
    private Storage storage;
    private TaskList tasks;

    public Duke() {
        ui = new Ui();
        storage = new Storage(DATA_FILEPATH);

        ArrayList<Task> savedTasks = storage.getSavedTasks();
        tasks = new TaskList(savedTasks);
    }

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
