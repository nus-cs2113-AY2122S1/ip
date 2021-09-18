package duke.command;

import duke.Storage;
import duke.TaskList;

public class ExitCommand extends Command {

    /**
     * Override superclass Command to not perform any actions.
     *
     * @param tasks   Not applicable.
     * @param storage Not applicable.
     */
    @Override
    public void runCommand(TaskList tasks, Storage storage) {
        // Nothing required for the implementation of exit command
    }

    /**
     * Detects that the exit command has been called.
     *
     * @return true
     */
    @Override
    public boolean isExitCommand() {
        return true;
    }
}
