package Duke.commands;

import Duke.Storage;
import Duke.TaskList;
import Duke.Ui;

public class IncorrectCommand extends Command {
    private String errorType;

    public IncorrectCommand(String errorType) {
        this.errorType = errorType;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showError(errorType);
    }
}
