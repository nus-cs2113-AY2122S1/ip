package duke.commands;

import duke.utilities.DukeException;
import duke.utilities.Storage;
import duke.utilities.TaskList;

public class DeleteCommand extends Command {

    public static final String COMMAND_WORD = "delete";
    public static final String COMMAND_WORD_HELP = "delete <task_number>";

    public DeleteCommand() {
    }

    /**
     * Executes the commands that deletes a task given by the user
     *
     * @param input Input of user
     * @param tasks TaskList of all the tasks
     * @param storage Storage of the bot
     * @throws DukeException If it is unable to delete task item successfully
     */
    @Override
    public void execute(String input, TaskList tasks, Storage storage) throws DukeException {
        tasks.deleteTask(input);
        storage.saveToFile(tasks.getTasks());
    }
}
