package duke.command;

import duke.Storage;
import duke.TaskList;

public class FindCommand extends Command{
    protected String taskDescription;

    public FindCommand(String taskDescription) {
        this.taskDescription = taskDescription;
    }

    @Override
    public void executeCommand(TaskList taskList, Storage storage) {
        taskList.findTask(taskDescription);
    }
}
