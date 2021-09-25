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

/**
 * Duke is a Command Line Interface chat-bot application designed to assist the user in scheduling
 * and keeping track of todos, deadlines and events.
 *
 * @author Ishaan Vyas
 * @version 0.2
 * @since 2021-25-9
 */
public class Duke {
    private final boolean IS_STARTING = true;
    private final boolean IS_ENDING = false;
    private final File DATA_DIRECTORY = new File("data");
    private final File DATA_FILE = new File(DATA_DIRECTORY.getPath().concat("/duke.txt"));
    private final Ui ui;
    private final Storage storage;
    private TaskList tasks;

    /**
     * Constructs Duke object by initializing ui, storage and TaskList. TaskList is initialized with
     * stored tasks if a valid storage file is found
     */
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

    /** 
     * Shows users Starting or Ending Messages and starts running Duke
     */
    public void start() {
        ui.showStartingOrEndingMessage(IS_STARTING);
        runDuke();
        ui.showStartingOrEndingMessage(IS_ENDING);
    }

    public static void main(String[] args) {
        new Duke().start();
    }

    /**
     * Runs Duke and continuously executes user commands until user issues the "bye" command
     */
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