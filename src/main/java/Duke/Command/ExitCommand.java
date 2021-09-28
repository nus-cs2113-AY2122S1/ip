package Duke.Command;

import Duke.Storage.Storage;
import Duke.Ui.Ui;
import Duke.Tasks.TaskList;

public class ExitCommand extends Command {
    public static final String COMMAND_WORD = "bye";
    public static final String COMMAND_DESCRIPTION = COMMAND_WORD
            + ": exit the programme.\n"
            + " Example: " + COMMAND_WORD;

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showByeMessage();
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
