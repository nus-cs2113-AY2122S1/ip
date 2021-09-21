package duke.command;

import duke.util.DukeException;
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
     * Executes the bye command.
     *
     * @param tasks   The TaskList object.
     * @param ui      The Ui object.
     * @param storage The Storage object,
     * @throws DukeException if incorrect usage.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        if (!argument.isEmpty()) {
            throw new DukeException(String.format(MESSAGE_FORMAT_GENERIC_USAGE, COMMAND_BYE));
        }

        ui.printExitMessage();
        this.isExit = true;
    }
}
