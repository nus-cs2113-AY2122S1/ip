package Duke;

import Duke.commands.Command;
import java.time.format.DateTimeParseException;

import static Duke.Constants.COMMAND_INCORRECT;
import static Duke.Constants.FILE;

public class Duke {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * Class constructor for duke, initialises ui, tasks and storage
     *
     * @param filePath Path to where the file that saves tasks is located
     */
    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = storage.load();
        } catch (DukeException | DateTimeParseException e) {
            ui.showError(FILE);
            System.exit(0);
        }
    }

    /**
     * Loop that runs program, terminates when isExit is set to true
     * Program reads user input and executes command
     */
    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                ui.showToUser("");
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (DukeException e) {
                ui.showError(COMMAND_INCORRECT);
            }
        }
    }

    /**
     * Provides file path to run the program
     *
     * @param args Not applicable
     */
    public static void main(String[] args) {
        new Duke("data/tasks.txt").run();
    }
}
