package duke;

import duke.command.Command;
import duke.exception.DukeException;
import duke.system.Parser;
import duke.system.Storage;
import duke.system.TaskList;
import duke.system.Ui;

/**
 * Represents the whole program.
 */
public class Duke {
    private final Storage storage;
    private final TaskList tasks;
    private final Ui ui;
    private final Parser parser;

    /**
     * The main function that will firstly execute when the program starts
     * @param args command-line arguments which comes with main(). Not used here.
     */
    public static void main(String[] args) {
        new Duke().run();
    }

    /**
     * The Duke constructor.
     */
    public Duke() {
        TaskList localTasks;
        this.ui = new Ui();
        this.parser = new Parser();
        this.storage = new Storage();
        try {
            localTasks = new TaskList(storage.readData());
        } catch (DukeException e) {
            ui.printErrorMessage(e.getMessage());
            localTasks = new TaskList();
        }

        this.tasks = localTasks;
    }

    /**
     * The main body of Duke where all components are integrated.
     */
    public void run() {
        ui.printGreetings();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                Command currentCommand = parser.parse(fullCommand);
                currentCommand.execute(tasks, ui, storage);
                isExit = currentCommand.isExit();
            } catch (DukeException e) {
                ui.printErrorMessage(e.getMessage());
            }
        }
    }

}
