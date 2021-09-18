package duke.command;

import duke.Storage;
import duke.TaskList;

public class HelpCommand extends Command {

    /**
     * Runs a command to print a list of possible commands.
     *
     * @param tasks   List that stores all the tasks.
     * @param storage Not applicable.
     */
    @Override
    public void runCommand(TaskList tasks, Storage storage) {
        tasks.printHelpMessage();
    }
}
