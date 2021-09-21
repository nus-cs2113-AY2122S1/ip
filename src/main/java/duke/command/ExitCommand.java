package duke.command;

import duke.util.Storage;
import duke.util.Ui;
import duke.task.TaskList;

public class ExitCommand extends Command {
    public ExitCommand(String argument) {
        super(argument);
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.printExitMessage();
        this.isExit = true;
    }
}
