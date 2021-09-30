package Commands;

import DukeClasses.Storage;
import DukeClasses.TaskList;
import DukeClasses.Ui;
import Exceptions.EmptyTaskException;

import java.io.IOException;

/**
 * Command that represents the user looking for instructions on using Duke
 */
public class HelpCommand extends Command {

    @Override
    public void executeCommand(TaskList tasks, Ui ui, Storage storage) throws EmptyTaskException, IOException {
        ui.showInstructions();
    }
}
