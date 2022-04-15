package duke;

import duke.commands.Command;
import duke.exceptions.DukeException;

public class Duke {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * Initializes new Duke object and loads memory data from specified file path.
     * If memory file is not found, a new file is created.
     * @param fileString path of file to load memory data from
     */
    public Duke(String fileString) {
        ui = new Ui();
        storage = new Storage(fileString);
        try {
            tasks = new TaskList(storage.load());
        } catch (Exception e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    /**
     * Repeatedly executes main programme loop until isExit == false.
     * Accepts command input from user and executes command.
     */
    public void run() {
        ui.showHello();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                ui.showLine(); // show the divider line ("_______")
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (DukeException e) {
                ui.showError(e.getMessage());
            } finally {
                ui.showLine();
            }
        }
    }

    public static void main(String[] args) {
        new Duke("./dukeMem.txt").run();
    }

}
