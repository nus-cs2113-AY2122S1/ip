package duke;

import duke.commands.Command;
import duke.commands.ExitCommand;
import duke.exceptions.DukeException;
import duke.exceptions.InvalidCommandException;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.tasks.TaskList;
import duke.ui.Ui;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

public class Duke {
    private final boolean IS_STARTING = true;
    private final boolean IS_ENDING = false;
    private final File DATA_DIRECTORY = new File("data");
    private final File DATA_FILE = new File(DATA_DIRECTORY.getPath().concat("/duke.txt"));
    private final Ui ui;
    private final Storage storage;
    private TaskList tasks;

    public Duke() {
        ui = new Ui();
        storage = new Storage(DATA_DIRECTORY, DATA_FILE);
        ui.showLoadingMessage();
        try {
            tasks = new TaskList(storage.load());
            ui.showLoadingSuccessful();
        } catch (FileNotFoundException fnfe) {
            ui.showDataNotFound();
            tasks = new TaskList();
        } catch (SecurityException se) {
            ui.showSecurityPermissionError();
        }
    }

    public void start() {
        ui.showStartingOrEndingMessage(IS_STARTING);
        runDuke();
        ui.showStartingOrEndingMessage(IS_ENDING);
    }

    public static void main(String[] args) {
        new Duke().start();
    }

    private void runDuke() {
        boolean conversationIsOver = false;
        while (!conversationIsOver) {
            try {
                String inputCommand = ui.readCommand();
                Command c = Parser.parse(inputCommand);
                c.execute(tasks, storage, ui);
                conversationIsOver = c instanceof ExitCommand;
            } catch (DukeException de) {
                ui.showError(de);
            } catch (InvalidCommandException ice) {
                ui.showHelpMessage();
            } catch (IOException ioe) {
                ui.showSaveError();
            } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
                ui.showNumberFormatError();
            }
        }
    }
}