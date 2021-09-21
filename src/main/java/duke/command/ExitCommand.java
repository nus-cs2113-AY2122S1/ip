package duke.command;

import duke.util.Storage;
import duke.util.Ui;
import duke.task.TaskList;

public class ExitCommand extends Command {
    /**
     * Constructor for ExitCommand class.
     *
     * @param argument The command argument.
     */
    public ExitCommand(String argument) {
        super(argument);
    }

    /**
     * Executes the exit command.
     *
     * @param tasks   The TaskList object.
     * @param ui      The Ui object.
     * @param storage The Storage object,
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.printExitMessage();
        this.isExit = true;
    }
}
