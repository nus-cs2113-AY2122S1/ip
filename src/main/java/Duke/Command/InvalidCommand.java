package Duke.Command;

import Duke.Storage.Storage;
import Duke.Tasks.TaskList;
import Duke.Ui.Ui;

public class InvalidCommand extends Command {
    public InvalidCommand() {

    }

    @Override
    /**
     * Execute the invalid command by showing invalid command message
     *
     * @param tasks TaskList the command to be executed on
     * @param ui Ui used for execution
     * @param storage Storage which the command may make change on
     */
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.printInvalidCommandMessage();
    }
}
