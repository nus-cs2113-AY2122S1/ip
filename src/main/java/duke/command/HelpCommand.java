package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

public class HelpCommand extends Command{
    @Override
    public void execute(TaskList taskList, Storage storage, Ui ui) {
        ui.printHelpMessage();
    }
}
