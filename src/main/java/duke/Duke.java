package duke;

import duke.command.Command;
import duke.exceptions.DukeException;

public class Duke {

    private Ui ui;
    private Storage storage;
    private Parser parser;
    private TaskList tasks;

    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath, ui);

        try {
            tasks = new TaskList(storage.readFile());
        } catch (DukeException e) {
            ui.printWithLines(e.toString());
            tasks = new TaskList();
        }

        parser = new Parser(ui, storage, tasks);
    }

    public void run() {
        ui.printHelloMessage();

        String input;

        input = ui.getUserCommand();

        while (true) {
            try {
                Command userCommand = parser.selectCommand(input);
                userCommand.execute();
                storage.saveFile(tasks);
            } catch (DukeException e) {
                ui.printWithLines(e.getMessage());
            }

            // Gets the next command entered
            input = ui.getUserCommand();
        }
    }

    public static void main(String[] args) {
        new Duke("duke_data/store.txt").run();
    }

}
