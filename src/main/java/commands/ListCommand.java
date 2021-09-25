package commands;

import static common.Message.MESSAGE_SEPARATOR;
import data.Storage;
import data.TaskList;
import ui.TextUI;

public class ListCommand extends Command {
    public static final String COMMAND_WORD = "/list";

    public ListCommand() {}

    @Override
    public void execute(TextUI ui, TaskList tasks, Storage data) {
        ui.showMessage(MESSAGE_SEPARATOR);
        for (int i = 0; i < tasks.getSize(); i++) {
            ui.showMessage(i + 1 + ". " + tasks.getTaskInfo(i));
        }
        ui.showMessage(MESSAGE_SEPARATOR);
    }
}
