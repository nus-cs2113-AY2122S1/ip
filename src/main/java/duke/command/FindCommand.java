package duke.command;

import duke.util.DukeException;
import duke.util.Storage;
import duke.util.Ui;
import duke.task.Task;
import duke.task.TaskList;

import java.util.ArrayList;

public class FindCommand extends Command {
    private static final String MESSAGE_NO_MATCH = "There are no matches.";
    private static final String MESSAGE_LIST_HEADER = "Here are the matching tasks:";

    private static final String MESSAGE_FORMAT_FIND_USAGE = "Usage: %s <search text>";

    /**
     * Constructor for FindCommand class.
     *
     * @param argument The command argument.
     */
    public FindCommand(String argument) {
        super(argument);
    }

    /**
     * Executes the find command.
     *
     * @param tasks   The TaskList object.
     * @param ui      The Ui object.
     * @param storage The Storage object.
     * @throws DukeException if no search string is provided.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        if (argument.isEmpty()) {
            throw new DukeException(String.format(MESSAGE_FORMAT_FIND_USAGE, COMMAND_FIND));
        }

        ArrayList<Task> matchingTasks = tasks.findTask(argument);

        if (matchingTasks.isEmpty()) {
            ui.printMessage(MESSAGE_NO_MATCH);
        } else {
            StringBuilder stringBuilder = new StringBuilder(MESSAGE_LIST_HEADER);
            for (int i = 0; i < matchingTasks.size(); i += 1) {
                stringBuilder.append("\n");
                stringBuilder.append(i + 1);
                stringBuilder.append(": ");
                stringBuilder.append(matchingTasks.get(i));
            }
            ui.printMessage(stringBuilder.toString());
        }
    }
}
