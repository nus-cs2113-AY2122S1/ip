package duke;

import duke.Exceptions.DukeException;
import duke.Commands.Command;


public class Duke {
    private final Storage storage;
    private TasksList tasks;
    private final Ui ui;

    /**
     * Initialises the main components (TaskList, Parser, Storage, Ui) of the application.
     * @param filePath Path to the file used for data storage.
     */
    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            storage.createStorageFile();
        } catch (DukeException e) {
            ui.showError(e.getMessage());
        }
        try {
            tasks = new TasksList(storage.load());
        } catch (DukeException e) {
            ui.showLoadingError();
            tasks = new TasksList();
        }
    }

    /**
     * Runs the application.
     * Takes in an input from the user, process it and execute the command.
     */
    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                ui.showLine();
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (DukeException e) {
                ui.showError(e.getMessage());
            } finally {
                ui.showLine();
            }
        }
    }

    public static void main(String[] args) {
        new Duke("data/tasks.txt").run();
    }
}
