package duke.command;

import duke.system.Storage;
import duke.system.TaskList;
import duke.system.Ui;
import duke.task.Task;

import java.util.ArrayList;

public class FindTasksCommand extends Command {

    private final String keywords;

    public FindTasksCommand(String keywords) {
        this.keywords = keywords;
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) {
        TaskList relevantTasks = new TaskList(tasks.getRelevantTaskList(this.keywords));
        ui.list(relevantTasks.getTaskList());
    }
}