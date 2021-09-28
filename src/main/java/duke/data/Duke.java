package duke.data;

import duke.commands.Command;
import duke.exception.DukeException;
import duke.exception.InvalidStorageFilePathException;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.ui.Ui;

import java.io.FileNotFoundException;
import java.util.Scanner;

public class Duke {
    // Constants
    public static final String FILE_NAME = "data/duke.txt";

    // Attributes
    private static Storage storage;
    private static TaskList tasks;
    private final Ui ui;

    // Constructor
    public Duke(String filePath) throws InvalidStorageFilePathException {
        ui = new Ui();
        storage = new Storage(filePath);

        try{
            tasks = new TaskList(storage.load());
            tasks.printList();
        } catch (DukeException e){
            ui.showLoadingError();
            tasks = new TaskList();
        }

    }

    public void run(){
        ui.showWelcome();
        boolean isExit = false;

        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                ui.showLine();
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

    // Main function
    public static void main(String[] args) throws DukeException, FileNotFoundException {
        new Duke(FILE_NAME).run();
    }

}
