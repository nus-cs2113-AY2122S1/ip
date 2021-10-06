package duke.commands;

import duke.components.Storage;
import duke.components.TaskList;
import duke.components.Ui;

/**
 * A representation of the command for exiting the program.
 */
public class ExitCommand extends Command {

    /**
     * Executes the exit command.
     *
     * @param tasks The ArrayList of tasks.
     * @param ui The component of Duke that deals with the interaction with the user.
     * @param storage The component of Duke that deals with loading tasks from the file and saving tasks in the file.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.printGoodBye();
        System.exit(0);
    }
}
