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
     * @param taskList not used in this subclass implementation
     * @param storage  not used in this subclass implementation
     * @param ui       not used in this subclass implementation
     */
    @Override
    public void execute(TaskList taskList, Storage storage, Ui ui) {
    }
}
