package duke.commands;

import duke.storage.DataStorage;
import duke.task.TaskList;

/**
 * Represents a command that can be executed by Duke.
 */
public abstract class Command {

    /**
     * Executes the command and configures Duke's {@code TaskList} and {@code DataStorage} accordingly.
     *
     * @param taskList Duke's {@code TaskList}
     * @param dataStorage Duke's {@code DataStorage}
     */
    public abstract void execute(TaskList taskList, DataStorage dataStorage);
}
