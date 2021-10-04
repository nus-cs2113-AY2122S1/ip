package duke.command;

import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

public class HelpCommand extends Command {
    public HelpCommand() {}

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        ui.printHelp();
    }
}
