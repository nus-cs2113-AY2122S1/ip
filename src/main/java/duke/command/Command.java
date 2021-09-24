package duke.command;

import duke.datasaver.DataManager;
import duke.task.TaskList;

public abstract class Command {

    public abstract void execute(TaskList taskList, DataManager dataManager);
}
