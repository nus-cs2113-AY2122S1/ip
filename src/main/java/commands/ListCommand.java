package commands;

import static common.Message.MESSAGE_SEPARATOR;
import data.Storage;
import data.TaskList;
import ui.TextUI;

/**
 * Represents the /list command
 * This command lists all currently existing tasks
 * COMMAND_WORD represents exact string user should provide to invoke this command
 */
public class ListCommand extends Command {
    public static final String COMMAND_WORD = "/list";

    /**
     * Sole constructor, no args
     */
    public ListCommand() {}

    /**
     * Upon execution, iterate through all tasks present within tasks container
     * and prints the string representation of all tasks which includes
     * type of task, status of task, description, when it is due (if applicable),
     * starting and ending times (if applicable)
     * Overrides method from parent class
     * @param ui Object that handles user IO
     * @param tasks Object that stores current and updated list of tasks
     * @param data Object that handles storage, read and writes to data.txt
     */
    @Override
    public void execute(TextUI ui, TaskList tasks, Storage data) {
        ui.showMessage(MESSAGE_SEPARATOR);
        for (int i = 0; i < tasks.getSize(); i++) {
            ui.showMessage(i + 1 + ". " + tasks.getTaskInfo(i));
        }
        ui.showMessage(MESSAGE_SEPARATOR);
    }
}
