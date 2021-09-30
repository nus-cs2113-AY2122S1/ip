package duke.command;

import duke.data.task.TaskList;
import duke.ui.Ui;
import duke.storage.Storage;

public class ExitCommand extends Command {


    /**
     * Exit the programme
     *
     * @param tasks   The list of tasks known
     * @param storage The storage that is used
     * @param ui      The ui that is used
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showGoodbyeMessage();
    }

    @Override
    public boolean isExit() {
        return true;
    }
}