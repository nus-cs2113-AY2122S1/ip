package duke.commands;

import duke.data.TaskList;
import duke.storage.Storage;
import duke.ui.TextUi;

public class ErrorCommand extends Command {

    private final String errorMessage;

    public ErrorCommand(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    @Override
    public void execute(TaskList taskList, TextUi ui, Storage storage) {
        ui.printMessage(errorMessage);
    }
}
