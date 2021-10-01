package duke.commands;

import duke.utilities.DukeException;
import duke.utilities.Storage;
import duke.utilities.TaskList;

public class ToDoCommand extends Command {

    public static final String COMMAND_WORD = "todo";
    public static final String COMMAND_WORD_HELP = "todo <description>";

    public ToDoCommand() {
    }

    /**
     * Executes the commands that adds a todo task
     *
     * @param input Input of user
     * @param tasks TaskList of all the tasks
     * @param storage Storage of the bot
     * @throws DukeException If it is unable to add todo item successfully
     */
    @Override
    public void execute(String input, TaskList tasks, Storage storage) throws DukeException {
        tasks.addTask(input, "T");
        storage.saveToFile(tasks.getTasks());
    };
}
