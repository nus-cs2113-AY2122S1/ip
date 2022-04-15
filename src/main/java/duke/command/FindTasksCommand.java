package duke.command;

import duke.exception.DukeException;
import duke.parser.Parser;
import duke.task.TaskManager;
import duke.ui.Ui;

/**
 * Command to find tasks based on a keyword.
 */
public class FindTasksCommand extends Command {
    private static final String COMMAND_FIND = "find";
    private final String line;

    /**
     * Constructs a FindTasksCommand with the user input.
     *
     * @param line The user input.
     */
    public FindTasksCommand(String line) {
        this.line = line;
    }

    /**
     * Executes the FindTasksCommand by filtering tasks
     * based on the keyword.
     *
     * @param taskManager TaskManager object used to filter tasks based on keyword.
     * @param ui          Ui object passed to the findTasks method.
     * @throws DukeException If keyword is empty.
     */
    @Override
    public void execute(TaskManager taskManager, Ui ui) throws DukeException {
        String keyword = Parser.parseDescription(line, COMMAND_FIND);
        taskManager.findTasks(keyword, ui);
    }
}
