package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.exception.DukeException;

public class ExitCommand extends Command {

    public static boolean isExit() {
        return isOver = true;
    }

    @Override
    public void executeUserCommand(TaskList taskList, Storage storage) throws DukeException {
    }
}
