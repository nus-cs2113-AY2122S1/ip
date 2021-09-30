package Commands;
import DukeClasses.*;
import Exceptions.EmptyTaskException;
import Exceptions.InvalidCommandException;

import java.io.IOException;

/**
 * Represents a user command
 */
public class Command {

    public void executeCommand(TaskList tasks, Ui ui, Storage storage) throws EmptyTaskException, IOException, InvalidCommandException {

    }

    /**
     * Returns true if the command is an exit command
     * @return true if command is an exit command
     */
    public boolean isExit() {
        return this instanceof ExitCommand;
    }
}
