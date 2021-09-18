package duke;

import duke.command.Command;
import duke.exceptions.DukeException;

public class Duke {

    private Ui ui;
    private Storage storage;
    private Parser parser;
    private TaskList tasks;

    /**
     * Duke constructor which initialises the User Interface(UI), Storage, Parser and TaskList.
     * @param filePath file path of duke_data, which stores data of Duke
     */
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

    /** Reads user command and executes it until the exit command is entered by the user. */
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
