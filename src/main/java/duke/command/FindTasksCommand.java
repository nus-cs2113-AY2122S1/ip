package duke.command;

import duke.exception.DukeException;
import duke.task.TaskManager;
import duke.ui.Ui;

/**
 * Command to find tasks based on a keyword
 */
public class FindTasksCommand extends Command {
    private final String keyword;

    /**
     * Constructs a FindTasksCommand with a keyword.
     *
     * @param keyword The keyword used to filter tasks.
     */
    public FindTasksCommand(String keyword) {
        this.keyword = keyword;
    }

    /**
     * Executes the FindTasksCommand by filtering tasks
     * based on the keyword.
     *
     * @param taskManager Used to filter tasks based on keyword.
     * @param ui          Passed to the findTasks method.
     * @throws DukeException If keyword is empty.
     */
    @Override
    public void execute(TaskManager taskManager, Ui ui) throws DukeException {
        taskManager.findTasks(keyword, ui);
    }
}
