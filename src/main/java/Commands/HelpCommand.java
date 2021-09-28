package Commands;

import DukeClasses.Storage;
import DukeClasses.TaskList;
import DukeClasses.Ui;
import Exceptions.EmptyTaskException;

import java.io.IOException;

public class HelpCommand extends Command {

    @Override
    public void executeCommand(TaskList tasks, Ui ui, Storage storage) throws EmptyTaskException, IOException {
        ui.showInstructions();
    }
}
