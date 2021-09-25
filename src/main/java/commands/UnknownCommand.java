package commands;

import data.Storage;
import data.TaskList;
import ui.TextUI;
import static common.Error.ERROR_INVALID_COMMAND;

public class UnknownCommand extends Command {
    public UnknownCommand() {}

    @Override
    public void execute(TextUI ui, TaskList tasks, Storage data) {
        ui.showMessage(ERROR_INVALID_COMMAND);
    }
}
