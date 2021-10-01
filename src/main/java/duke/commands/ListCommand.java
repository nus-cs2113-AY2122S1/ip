package duke.commands;

import duke.utilities.DukeException;
import duke.utilities.Storage;
import duke.utilities.TaskList;
import duke.utilities.Ui;

public class ListCommand extends Command {

    public static final String COMMAND_WORD = "list";

    public ListCommand() {
    }

    /**
     * Executes the commands that displays the tasks currently
     *
     * @param input Input of user
     * @param tasks TaskList of all the tasks
     * @param storage Storage of the bot
     * @throws DukeException If it is unable to display any tasks properly
     */
    @Override
    public void execute(String input, TaskList tasks, Storage storage) throws DukeException {
        Ui.printList(tasks.getTasks());
    }
}
