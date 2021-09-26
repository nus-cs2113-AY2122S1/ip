package commands;

import data.Storage;
import data.TaskList;
import ui.TextUI;

import static common.Message.MESSAGE_SEPARATOR;
import static common.Error.ERROR_FORMAT_FIND;

/**
 * Represents the /find command
 * This command lists all tasks that contain a specified search term within their
 * descriptions.
 * COMMAND_WORD represents exact string user should provide to invoke this command
 */
public class FindCommand extends Command {
    public static final String COMMAND_WORD = "/find";
    protected String args;
    protected String searchTerm;

    /**
     * Sole constructor
     * @param args Additional arguments supplied by user after COMMAND_WORD
     */
    public FindCommand(String args) {
        this.args = args;
    }

    /**
     * Splits arguments supplied user after COMMAND_WORD into search term.
     * Iterates through task container, print string representation of task
     * if their descriptions contain the search term.
     * Error messages also output if command is malformed.
     * @param ui Object that handles user IO
     * @param tasks Object that stores current and updated list of tasks
     * @param data Object that handles storage, read and writes to data.txt
     */
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
