package duke;

import duke.commands.Command;
import duke.parse.Parser;
import duke.storage.Storage;
import duke.tasklist.TaskList;
import duke.ui.Ui;

import java.io.FileNotFoundException;

/**
 * Initiate the program with welcome message and loading of saved data
 * Starts user interaction
 */
public class Duke {
    private final Ui ui;
    private final TaskList taskList;
    private final Storage storage;

    public Duke() {
        this.ui = new Ui();
        this.taskList = new TaskList();
        this.storage = new Storage();
    }

    /**
     * Show welcome message
     */
    public void initiateDuke() {
        ui.printWelcome();

        try {
            taskList.setTaskList(storage.load());
        } catch (DukeException e) {
            ui.printDukeException(e.toString());
        }

        run();
    }

    /**
     * Interact with user and takes user input until user chooses to exit
     */
    public void run() {
        boolean isExit = false;

        while (!isExit) {
            try {
                String line = ui.getUserInput();
                Command c = Parser.parse(line.trim(), taskList);
                c.execute(taskList, ui, storage);
                isExit = c.isExit();
            } catch (DukeException e) {
                ui.printDukeException(e.toString());
            }
        }

        exit();
    }

    /**
     * Prints bye message and end program
     */
    public void exit() {
        ui.printBye();
        System.exit(0);
    }

    public static void main(String[] args) {
        new Duke().initiateDuke();
    }
}
