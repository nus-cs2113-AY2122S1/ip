package duke.data;

import duke.commands.Command;
import duke.exception.DukeException;
import duke.exception.InvalidStorageFilePathException;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.ui.Ui;

import java.io.FileNotFoundException;

/**
 * Entry point of the Duke application.
 * Initializes the application and starts the interaction with the user.
 *
 * @author Jiang Yuxin
 * @since 28/09/2021
 */
public class Duke {
    // Constants
    public static final String FILE_NAME = "data/duke.txt";

    // Attributes
    private static Storage storage;
    private static TaskList tasks;
    private final Ui ui;

    // Constructor
    public Duke(String filePath) throws InvalidStorageFilePathException {
        ui = new Ui();
        ui.showLogo();
        storage = new Storage(filePath);

        try {
            tasks = new TaskList(storage.load());
            tasks.printList();
        } catch (DukeException e) {
            ui.showError(e.getMessage());
            tasks = new TaskList();
        }

    }

    /** Runs the program until termination. */
    public void run(){
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

    // Main function
    public static void main(String[] args) throws DukeException {
        new Duke(FILE_NAME).run();
    }

}
