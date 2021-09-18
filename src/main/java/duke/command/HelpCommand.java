package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

public class HelpCommand extends Command {

    /**
     * Runs a command to print a list of possible commands.
     *
     * @param tasks   List that stores all the tasks.
     * @param ui      User interface of duke.
     * @param storage Not applicable.
     */
    @Override
    public void runCommand(TaskList tasks, Ui ui, Storage storage) {
        tasks.printHelpMessage(ui);
    }
}
