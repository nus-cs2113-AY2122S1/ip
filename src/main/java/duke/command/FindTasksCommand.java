package duke.command;

import duke.exception.DukeException;
import duke.task.TaskManager;
import duke.ui.Ui;

public class FindTasksCommand extends Command {
    private final String keyword;

    public FindTasksCommand(String keyword) {
        this.keyword = keyword;
    }

    @Override
    public void execute(TaskManager taskManager, Ui ui) throws DukeException {
        taskManager.findTasks(keyword, ui);
    }
}
