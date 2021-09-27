package duke;

import duke.commands.Command;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.storage.Storage.StorageException;
import duke.tasklist.TaskList;
import duke.ui.Ui;

/**
 * A personal assistant chatbot.
 */
public class Duke {
    public static final String DATA_FILE_SEPARATOR = " ` ";

    private static final String MESSAGE_ERROR = "☹ OOPS!!! %1$s";

    private static Storage storage;
    private static TaskList tasks;
    private static Ui ui;

    // Code below inspired by https://nus-cs2113-ay2122s1.github.io/website/schedule/week7/project.html
    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = storage.loadData();
        } catch (StorageException e) {
            ui.showToUser(e.getMessage());
            throw new RuntimeException(e);
        }
    }

    // Code below inspired by https://nus-cs2113-ay2122s1.github.io/website/schedule/week7/project.html
    public void run() {
        ui.showGreeting(storage.getPath(), storage.isUsingNewFile());
        boolean isExit = false;
        while (!isExit) {
            try {
                final String userInput = ui.getUserInput();
                final Command command = Parser.parseCommand(userInput);
                isExit = command.isExit();
                final String feedback = command.execute(tasks, ui, storage);
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

    /**
     * Main entry point of Duke.
     */
    public static void main(String[] args) {
        new Duke("data/duke.txt").run();
    }
}
