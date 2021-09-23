package command;

import storage.Storage;
import task.TaskList;
import ui.Ui;

public class ExitCommand extends Command {
    public static final String COMMAND_KEYWORD = "bye";

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
    }
}
