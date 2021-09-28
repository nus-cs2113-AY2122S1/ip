package Command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

/**
 * Represents a <code>Command</code> to exit the program.
 */
public class ExitCommand extends Command {
    /**
     * Instantiates a <code>ExitCommand</code> object.
     *
     * @param description Description of the task.
     */
    public ExitCommand(String description) {
        super(description, IS_EXIT);
    }

    /**
     * Exits the programme.
     *
     * @param tasks Tasks to be executed on.
     * @param ui UI to interact with user.
     * @param storage Storage to save task changes.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        exit();
    }

    private void exit() {
        return;
    }
}
