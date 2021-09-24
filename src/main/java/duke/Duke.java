package duke;

import duke.command.Command;
import duke.exception.InvalidCommandException;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

import java.io.FileNotFoundException;

public class Duke {

    private Ui ui;
    private TaskList tasks;
    private Storage storage;

    public Duke() {
        ui = new Ui();
        storage = new Storage();
        try {
            tasks = new TaskList(storage.loadData());
        } catch (FileNotFoundException e) {
            tasks = new TaskList();
        }
    }

    public void run() {
        ui.greet();
        boolean isExit = false;
        while (!isExit) {
            try {
                String userCommand = ui.getCommand();
                Command c = Parser.parse(userCommand);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (InvalidCommandException e) {
                ui.printInvalidMessage();
            }
        }
    }

    public static void main(String[] args) {
        new Duke().run();
    }

}