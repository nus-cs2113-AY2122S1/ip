package duke.commands;

import duke.utilities.DukeException;
import duke.utilities.Storage;
import duke.utilities.TaskList;
import duke.utilities.Ui;

public class DoneCommand extends Command {

    public static final String COMMAND_WORD = "done";

    public DoneCommand() {
    }

    /**
     * Executes the commands that sets a task as done
     *
     * @param input Input of user
     * @param tasks TaskList of all the tasks
     * @param ui Ui of the bot
     * @param storage Storage of the bot
     * @throws DukeException If it is unable to set task item successfully
     */
    @Override
    public void execute(String input, TaskList tasks, Ui ui, Storage storage) throws DukeException {
        tasks.setTaskAsDone(input, ui);
        storage.saveToFile(tasks.getTasks());
    }
}
