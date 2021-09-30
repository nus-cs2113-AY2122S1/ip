package duke.commands;

import duke.task.Task;
import duke.utilities.DukeException;
import duke.utilities.Storage;
import duke.utilities.TaskList;
import duke.utilities.Ui;

public class DeadlineCommand extends Command {

    public static final String COMMAND_WORD = "deadline";
    public static final String COMMAND_WORD_HELP = "deadline <description> /by <time>";

    public DeadlineCommand() {
    }

    /**
     * Executes the commands that adds a deadline task
     *
     * @param input Input of user
     * @param tasks TaskList of all the tasks
     * @param ui Ui of the bot
     * @param storage Storage of the bot
     * @throws DukeException If it is unable to add deadline item successfully
     */
    @Override
    public void execute(String input, TaskList tasks, Ui ui, Storage storage) throws DukeException {
        tasks.addTask(input, ui, "D");
        storage.saveToFile(tasks.getTasks());
    }
}
