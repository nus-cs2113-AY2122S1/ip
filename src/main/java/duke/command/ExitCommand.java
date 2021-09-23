package duke.command;

import duke.Storage;
import duke.TaskList;

/**
 * Class that defines and represents the behaviour of the Exit command
 */
public class ExitCommand extends Command{

    /**
     * Constructor sets isExit in Parent Command class to true
     */
    public ExitCommand() {
        hasExecutedExitCommand();
    }

    /**
     * Does nothing in this case
     * @param taskList Task Manager that executes task based on the given command
     * @param storage Stores User data in a csv file
     */
    @Override
    public void executeCommand(TaskList taskList, Storage storage) {
    }
}
