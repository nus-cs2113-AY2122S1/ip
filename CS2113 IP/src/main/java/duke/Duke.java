package duke;

import duke.command.Command;
import duke.exception.DukeException;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.ui.Ui;

import java.io.IOException;
import java.time.format.DateTimeParseException;

public class Duke {
    final static private String filePath = Storage.getFilePath();
    private final Ui ui;
    private final Storage storage;
    private static TaskList tasks;

    /**
     * Constructor of Duke class, that creates the database if it does not exist, and loads the database.
     *
     * @param filePath File path of database
     * @throws IOException   If there is file creation, file read/write error
     * @throws DukeException If there is error during loading of database
     */
    public Duke(String filePath) throws IOException, DukeException {
        ui = new Ui();
        storage = new Storage();
        storage.createDirectory();
        storage.createFile(ui);
        try {
            tasks = new TaskList(filePath);
        } catch (DukeException e) {
            ui.showError(e.getMessage());
        } catch (IOException e) {
            ui.showLoadingError();
        }
        tasks = new TaskList();
    }

    /**
     * Executes the main part of Duke.
     *
     * @throws IOException               If there is file creation, read/write error
     * @throws DateTimeParseException    If there is an error with format of date/time input from String
     * @throws IndexOutOfBoundsException If the input is out-of-bounds
     */
    public void run() throws IOException, DateTimeParseException, IndexOutOfBoundsException {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (DukeException e) {
                ui.showError(e.getMessage());
            } catch (DateTimeParseException e) {
                ui.showDeadlineError();
            } catch (IndexOutOfBoundsException e) {
                ui.showOutOfBoundsError();
            } finally {
                storage.writeFile(tasks);
                ui.showHorizontalLine();
            }
        }
    }

    /**
     * Main method of Duke.
     *
     * @param args Not used
     * @throws IOException   If there is file creation, read/write error
     * @throws DukeException If there is general errors within program
     */
    public static void main(String[] args) throws IOException, DukeException {
        new Duke(filePath).run();
    }
}
