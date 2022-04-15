package duke.command;

import duke.util.DukeException;
import duke.util.Storage;
import duke.util.Ui;
import duke.task.TaskList;

public class ListCommand extends Command {
    private static final String MESSAGE_LIST_EMPTY = "Task list is empty.";

    /**
     * Constructor for ListCommand class.
     *
     * @param argument The command argument.
     */
    public ListCommand(String argument) {
        super(argument);
    }

    /**
     * Executes the list command.
     *
     * @param tasks   The TaskList object.
     * @param ui      The Ui object.
     * @param storage The Storage object.
     * @throws DukeException if incorrect usage.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        if (!argument.isEmpty()) {
            throw new DukeException(String.format(MESSAGE_FORMAT_GENERIC_USAGE, COMMAND_LIST));
        }

        if (tasks.isEmpty()) {
            ui.printMessage(MESSAGE_LIST_EMPTY);
        } else {
            ui.printMessage(tasks.toString());
        }
    }
}
