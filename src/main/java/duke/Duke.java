package duke;

import static duke.ui.Strings.MESSAGE_DIRECTORY_CREATION_EXCEPTION;
import static duke.ui.Strings.MESSAGE_INVALID_TASK_TYPE_EXCEPTION;
import static duke.ui.Strings.MESSAGE_LOAD_EXCEPTION;

import duke.commands.Command;
import duke.data.TaskList;
import duke.data.exception.DirectoryCreationException;
import duke.data.exception.InvalidTaskTypeException;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.ui.TextUi;
import java.io.FileNotFoundException;

public class Duke {

    private static TextUi ui;
    private Storage storage;
    private TaskList tasks;

    /**
     * Set up Duke, Create TextUi, Storage and Tasklist objects
     *
     * @param filePath filepath of the save file to read from/write to
     */
    public Duke(String filePath) {
        ui = new TextUi();
        tasks = new TaskList();
        try {
            storage = new Storage(filePath);
            tasks = new TaskList(storage.load());
        } catch (FileNotFoundException e) {
            ui.printMessage(MESSAGE_LOAD_EXCEPTION);
            tasks = new TaskList();
        } catch (DirectoryCreationException e) {
            ui.printMessage(MESSAGE_DIRECTORY_CREATION_EXCEPTION);
        } catch (InvalidTaskTypeException e) {
            ui.printMessage(MESSAGE_INVALID_TASK_TYPE_EXCEPTION);
        }
    }
    
    public void run() {
        ui.printGreeting();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                ui.showLine(); // show the divider line ("_______")
                Command c = Parser.parseCommand(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
                //} catch (DukeException e) {
                //    ui.showError(e.getMessage());
            } finally {
                ui.showLine();
            }
        }
    }

    public static void main(String[] args) {
        new Duke("data/tasks.txt").run();
    }
}