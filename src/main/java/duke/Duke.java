package duke;

import duke.commands.Command;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.storage.Storage.StorageException;
import duke.tasklist.TaskList;
import duke.ui.Ui;

/**
 * Entry point of the Duke chatbot application.
 * Initializes the application and starts the interaction with the user.
 */
public class Duke {
    private static final String MESSAGE_ERROR = "☹ OOPS!!! %1$s";

    private static Storage storage;
    private static TaskList tasks;
    private static Ui ui;

    /**
     * Creates an instance of the Duke chatbot application, and loads data from the given {@code filePath}.
     *
     * @param filePath Path of the file where the data is stored in.
     */
    public Duke(String filePath) {
        // Code below inspired by https://nus-cs2113-ay2122s1.github.io/website/schedule/week7/project.html
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = storage.loadData();
        } catch (StorageException e) {
            ui.showToUser(e.getMessage());
            throw new RuntimeException(e);
        }
    }

    /**
     * Runs the program until termination.
     * Shows the greeting message, and then keeps reading user commands and executing them until the exit command is
     * given.
     */
    public void run() {
        // Code below inspired by https://nus-cs2113-ay2122s1.github.io/website/schedule/week7/project.html
        ui.showGreeting(storage.getPath(), storage.isUsingNewFile());
        boolean isExit = false;
        while (!isExit) {
            try {
                final String userInput = ui.getUserInput();
                final Command command = Parser.parseCommand(userInput);
                isExit = command.isExit();
                final String feedback = command.execute(tasks);
                storage.saveData(tasks);
                ui.showToUser(feedback);
            } catch (StorageException e) {
                ui.showToUser(e.getMessage());
                throw new RuntimeException(e);
            } catch (DukeException e) {
                ui.showToUser(String.format(MESSAGE_ERROR, e.getMessage()));
            }
        }
    }

    /** Main entry point of Duke. */
    public static void main(String[] args) {
        new Duke("data/duke.txt").run();
    }
}
