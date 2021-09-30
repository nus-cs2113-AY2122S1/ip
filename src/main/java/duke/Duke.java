package duke;

import duke.commands.Command;
import duke.common.Messages;
import duke.data.TaskList;
import duke.data.exception.EmptyTaskException;
import duke.data.exception.InvalidException;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.ui.TextUi;


/**
 * Entry point of the Duke application.
 * Initialises the program and starts interaction with the user.
 */
public class Duke {

    private Storage storage;
    private TaskList tasks;
    private TextUi ui;

    /**
     * Instantiates a new TaskList, TextUi, and Storage.
     * Loads up the data from the storage file.
     */
    public Duke() {
        tasks = new TaskList();
        ui = new TextUi();
        storage = new Storage(tasks);
        storage.initTaskList();
    }

    /**
     * Shows the user the welcome message.
     * Receive input from the user and runs the program until termination.
     */
    public void run() {
        ui.showWelcomeMessage();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.getInput();
                ui.showLine(); // show the divider line ("_______")
                Command c = Parser.parseCommand(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = c.getExit();
            } catch (InvalidException e) {
                ui.showToUser(Messages.MESSAGE_INVALID_COMMAND);
            } catch (EmptyTaskException e) {
                ui.showToUser(Messages.MESSAGE_EMPTY_TASK_DESCRIPTION);
            } catch (IndexOutOfBoundsException e) {
                ui.showToUser(Messages.MESSAGE_INVALID_TASK_INDEX);
            } finally {
                ui.showLine();
            }
        }
    }

    public static void main(String[] args) {
        new Duke().run();
    }
}
