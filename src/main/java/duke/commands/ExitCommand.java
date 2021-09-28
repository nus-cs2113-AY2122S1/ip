package duke.commands;

import duke.common.Messages;
import duke.data.TaskList;
import duke.storage.Storage;
import duke.ui.TextUi;

public class ExitCommand extends Command {
    public static final String COMMAND_WORD = "bye";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Exits the program. "
            + "\n|| "
            + "Example: " + COMMAND_WORD + "\n||";

    @Override
    public void execute(TaskList tasks, TextUi ui, Storage storage) {
        ui.showToUser(ui.DASHES, Messages.MESSAGE_GOODBYE, ui.DASHES);
        isExit = true;
    }
}