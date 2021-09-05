package duke.command;

import duke.TaskManager;

public class HelpCommand extends Command {

    /**
     * Runs a command to print a lis of possible commands.
     */
    @Override
    public void runCommand() {
        TaskManager.printHelpMessage();
    }
}
