package duke.commands;

import duke.utilities.DukeException;
import duke.utilities.Storage;
import duke.utilities.TaskList;

public class FindCommand extends Command {

    public static final String COMMAND_WORD = "find";
    public static final String COMMAND_WORD_HELP = "find <keyword>";

    public FindCommand() {
    }

    /**
     * Executes the commands that finds tasks related to keyword given
     *
     * @param input Input of user
     * @param tasks TaskList of all the tasks
     * @param storage Storage of the bot
     * @throws DukeException If it is unable to find tasks successfully
     */
    @Override
    public void execute(String input, TaskList tasks, Storage storage) throws DukeException {
        tasks.findTasks(input);
    };
}
