package duke.processes.commands;

import duke.processes.utility.Interface;

public class HelpCommand extends Command {

    public HelpCommand() {
    }

    /**
     * Displays all the possible commands that can be used in this program for the user to
     * reference
     *
     * @return CommandResult indicating the end of the commands
     */
    public CommandResult execute() {
        Interface.displayCommandList();
        return new CommandResult("---------end of commands-----------");
    }
}
