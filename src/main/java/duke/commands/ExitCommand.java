package duke.commands;

import duke.Storage;
import duke.TasksList;
import duke.Ui;


public class ExitCommand extends Command {
    @Override
    public boolean isExit() {
        return true;
    }

    public void execute(TasksList taskList, Ui ui, Storage storage) {
        ui.showGoodbye();
    };
}