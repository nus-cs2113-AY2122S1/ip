package duke.command;

import duke.system.Storage;
import duke.system.TaskList;
import duke.system.Ui;

public class ListAllCommand extends Command {

    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.list(tasks.getTaskList());
    }
}