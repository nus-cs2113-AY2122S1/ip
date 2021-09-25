package commands;

import data.Storage;
import data.TaskList;
import ui.TextUI;

import static common.Message.MESSAGE_SEPARATOR;
import static common.Error.ERROR_FORMAT_FIND;

public class FindCommand extends Command {
    public static final String COMMAND_WORD = "/find";
    protected String args;
    protected String searchTerm;

    public FindCommand(String args) {
        this.args = args;
    }

    @Override
    public void execute(TextUI ui, TaskList tasks, Storage data) {
        try {
            searchTerm = args.substring(6);
            ui.showMessage(MESSAGE_SEPARATOR);
            for (int i = 0; i < tasks.getSize(); i++) {
                String description = tasks.getTask(i).getDescription();
                if (description.contains(searchTerm)) {
                    ui.showMessage(tasks.getTaskInfo(i));
                }
            }
            ui.showMessage(MESSAGE_SEPARATOR);
        } catch (StringIndexOutOfBoundsException e) {
            ui.showMessage(ERROR_FORMAT_FIND);
        }
    }
}
