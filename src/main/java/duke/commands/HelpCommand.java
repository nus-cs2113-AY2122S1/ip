package duke.commands;

import duke.utilities.DukeException;
import duke.utilities.Storage;
import duke.utilities.TaskList;
import duke.utilities.Ui;

public class HelpCommand extends Command {

    public static final String COMMAND_WORD = "help";
    public HelpCommand() {
    }

    /**
     * Executes the commands that displays help
     *
     * @param input Input of user
     * @param tasks TaskList of all the tasks
     * @param storage Storage of the bot
     * @throws DukeException If it is unable to retrieve help
     */
    @Override
    public void execute(String input, TaskList tasks, Storage storage) throws DukeException {
        Ui.printHelp();
    };
}
