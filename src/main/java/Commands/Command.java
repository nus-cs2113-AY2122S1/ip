package Commands;
import DukeClasses.*;
import Exceptions.EmptyTaskException;
import Exceptions.InvalidCommandException;

import java.io.IOException;


public class Command {



    public void executeCommand(TaskList tasks, Ui ui, Storage storage) throws EmptyTaskException, IOException, InvalidCommandException {

    }


    public boolean isExit() {
        return this instanceof ExitCommand;
    }
}
