package duke.commands;

import duke.utilities.DukeException;
import duke.utilities.Storage;
import duke.utilities.TaskList;

public class EventCommand extends Command {

    public static final String COMMAND_WORD = "event";
    public static final String COMMAND_WORD_HELP = "event <description> /at <time>";

    public EventCommand() {
    }

    /**
     * Executes the commands that adds a event task
     *
     * @param input Input of user
     * @param tasks TaskList of all the tasks
     * @param storage Storage of the bot
     * @throws DukeException If it is unable to add event item successfully
     */
    @Override
    public void execute(String input, TaskList tasks, Storage storage) throws DukeException {
        tasks.addTask(input, "E");
        storage.saveToFile(tasks.getTasks());
    };
}
