package commands;

import data.Storage;
import data.TaskList;
import ui.TextUI;

import static common.Message.MESSAGE_EXIT;

public class ByeCommand extends Command{
    public static final String COMMAND_WORD = "/bye";

    public ByeCommand() {}

    @Override
    public void execute(TextUI ui, TaskList tasks, Storage data) {
        ui.showMessage(MESSAGE_EXIT);
    }
}
