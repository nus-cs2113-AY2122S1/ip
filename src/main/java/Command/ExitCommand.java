package Command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

public class ExitCommand extends Command {
    public ExitCommand(String description) {
        super(description, IS_EXIT);
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        exit();
    }

    private void exit() {
        return;
    }
}
