package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

public class ExitCommand extends Command {

    /**
     * Returns true as the Command type is exitCommand
     *
     * @return true
     */
    @Override
    public boolean isExit() {
        return true;
    }

    /**
     * Does nothing
     *
     * @param taskList Does not matter in this subclass implementation
     * @param ui The ui class instance that will print out the completion message
     */
    @Override
    public void execute(TaskList taskList, Storage storage, Ui ui) {
    }
}
