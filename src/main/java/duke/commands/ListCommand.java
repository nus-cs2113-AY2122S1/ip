package duke.commands;

import duke.storage.Storage;
import duke.ui.Ui;
import duke.tasks.TaskList;

public class ListCommand extends Command {
    public static final String COMMAND_WORD = "list";

    @Override
    public void execute(TaskList tasks, Storage storage, Ui ui) {
        ui.showTasks(tasks.getTasks(), tasks.getNumberOfTasks());
    }
}
