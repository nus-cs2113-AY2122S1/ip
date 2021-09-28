package duke.commands;

import duke.Duke;
import duke.task.Task;
import duke.utilities.DukeException;
import duke.utilities.Storage;
import duke.utilities.TaskList;
import duke.utilities.Ui;

public class ToDoCommand extends Command {

    public static final String COMMAND_WORD = "todo";

    public ToDoCommand() {
    }

    /**
     * Executes the commands that adds a todo task
     *
     * @param input Input of user
     * @param tasks TaskList of all the tasks
     * @param ui Ui of the bot
     * @param storage Storage of the bot
     * @throws DukeException If it is unable to add todo item successfully
     */
    @Override
    public void execute(String input, TaskList tasks, Ui ui, Storage storage) throws DukeException {
        tasks.addTask(input, ui, "T");
        storage.saveToFile(tasks.getTasks());
    };
}
