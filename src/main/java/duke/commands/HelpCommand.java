package duke.commands;

import duke.utilities.DukeException;
import duke.utilities.Storage;
import duke.utilities.TaskList;
import duke.utilities.Ui;

public class HelpCommand extends Command {

    public static final String COMMAND_WORD = "help";
    public HelpCommand() {
    }

    @Override
    public void execute(String input, TaskList tasks, Ui ui, Storage storage) throws DukeException {
        ui.printHelp();
    };
}
