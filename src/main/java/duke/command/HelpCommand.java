package duke.command;

import duke.DukeException;
import duke.Ui;

public class HelpCommand implements Command {

    /**
     * Displays help message for all possible commands and their format
     *
     * @param printMessage Print message to user on executing command. Value is irrelevant
     *                     as values will be printed out anyways
     */
    @Override
    public void run(boolean printMessage) throws DukeException {
        Ui.printHelpMessage();
    }

    /**
     * @return Type of command
     */
    @Override
    public CommandType getType() {
        return CommandType.HELP;
    }
}