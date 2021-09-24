package duke;

import duke.command.Command;
import duke.exception.DukeException;
import duke.system.Parser;
import duke.system.Storage;
import duke.system.TaskList;
import duke.system.Ui;


import java.io.IOException;
import java.util.ArrayList;

public class Duke {

    private final Storage storage;
    private final TaskList tasks;
    private final Ui ui;
    private final Parser parser;

    public static void main(String[] args) {
        new Duke().run();
    }

    public Duke() {
        TaskList tasks1;
        ui = new Ui();
        parser = new Parser();
        storage = new Storage();
        try {
            tasks1 = new TaskList(storage.readData());
        } catch (DukeException e) {
            ui.printErrorMessage(e.getMessage());
            tasks1 = new TaskList();
        }

        tasks = tasks1;
    }

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
        ui.printBye();
    }

}
