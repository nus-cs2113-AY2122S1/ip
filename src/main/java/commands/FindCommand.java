package commands;

import console.InputParser;
import task.TaskManager;

/**
 * Filters tasks by keyword.
 */
public class FindCommand extends Command {

    public static final String COMMAND_WORD = "find";

    /**
     * Creates a new find command.
     *
     * @param taskManager The TaskManager object to execute operations on.
     */
    public FindCommand(TaskManager taskManager) {
        super(taskManager);
    }

    /**
     * Displays the filtered tasks to the user.
     *
     * @return The find command type.
     */
    @Override
    public String executeCommand() {
        taskManager.findTask(InputParser.getKeyword(commandComponents));
        return COMMAND_WORD;
    }
}
