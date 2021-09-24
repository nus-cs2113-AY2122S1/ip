package duke.commands;

import duke.storage.DataStorage;
import duke.task.TaskList;

public abstract class Command {

    public abstract void execute(TaskList taskList, DataStorage dataStorage);
}
