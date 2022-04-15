package duke.commands;

import duke.utilities.DukeException;
import duke.utilities.Storage;
import duke.utilities.TaskList;

public class ByeCommand extends Command {

    public static final String COMMAND_WORD = "bye";

    public ByeCommand() {
    }
    /**
     * Quits the program
     *
     * @param input Input of user
     * @param tasks TaskList of all the tasks
     * @param storage Storage of the bot
     */
    @Override
    public void execute(String input, TaskList tasks, Storage storage) throws DukeException {
    }
}
