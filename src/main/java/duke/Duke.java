package duke;

import duke.command.Command;
import duke.exception.DukeException;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

public class Duke {

    private final Ui ui;
    private TaskList tasks;
    private final Storage storage;

    /**
     * Start up Duke with previously stored task if any
     * @param filePath location of where to read and write task
     */
    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.loadData());
        } catch (DukeException e) {
            ui.printErrorMessage(e.getMessage());
        }
    }

    public static void main(String[] args) {
        new Duke("data/tasks.txt").run();
    }

    public void run() {
        ui.printWelcomeMessage();
        boolean isExit = false;
        // Listen for user input and do command given by user till user wants to exit program
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                ui.printHorizontalLines();
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (DukeException e) {
                ui.printErrorMessage(e.getMessage());
            } finally {
                ui.printHorizontalLines();
            }
        }
    }
}
