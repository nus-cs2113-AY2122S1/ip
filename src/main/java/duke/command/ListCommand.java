package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.exception.DukeException;

public class ListCommand extends Command {

    @Override
    public void executeUserCommand(TaskList taskList, Storage storage) throws DukeException {
        taskList.listTasks();
    }
}
