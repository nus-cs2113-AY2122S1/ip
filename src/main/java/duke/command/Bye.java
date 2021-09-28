package duke.command;

import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

public class Bye extends Command{

    /**
     * Exits Duke and saves all the newly added tasks.
     *
     * @param tasks TaskList including all tasks.
     * @param ui User Interface.
     * @param storage Storage to load and save the data file.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.exit();
        storage.saveData(tasks.getTasks());
    }

    /**
     * Returns true to indicate the program stops running.
     *
     * @return boolean true.
     */
    @Override
    public boolean isExit() {
        return true;
    }
}
