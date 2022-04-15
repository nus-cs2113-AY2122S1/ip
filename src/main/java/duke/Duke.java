package duke;

import duke.commands.Command;
import duke.exceptions.DukeException;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.tasks.TaskList;
import duke.ui.Ui;

/**
 * Initializes Duke program and tries to load data from storage
 * Prompts for user input and executes commands accordingly
 */
public class Duke {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public Duke() {
        ui = new Ui();
        storage = new Storage();
        tasks = new TaskList(storage.loadData());
    }

    public static void main(String[] args) {
        new Duke().run();
    }

    /**
     * Prompt user for input to execute commands.
     * Program will terminate when `bye` command is received.
     */
    public void run() {
        ui.displayGreetingMessage();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                Command cmd = Parser.parseCommand(fullCommand);
                cmd.execute(tasks, ui, storage);
                isExit = cmd.isExit();
            } catch (DukeException e) {
                ui.displayUnknownCommandResponse();
            }
        }
    }
}
