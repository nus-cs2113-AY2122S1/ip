package duke.command;

import duke.Storage;
import duke.TaskList;

public class ExitCommand extends Command{

    public ExitCommand() {
        hasExecutedExitCommand();
    }

    @Override
    public void executeCommand(TaskList taskList, Storage storage) {
    }
}
