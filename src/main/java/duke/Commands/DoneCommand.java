package duke.Commands;

import duke.Exceptions.DukeException;
import duke.Exceptions.InvalidTaskIndexException;
import duke.Storage;
import duke.TasksList;
import duke.Ui;


public class DoneCommand extends Command {
    public int taskIndex;

    public DoneCommand(int taskIndex) {
        this.taskIndex = taskIndex;
    }

    public void execute(TasksList taskList, Ui ui, Storage storage) throws DukeException {
        try {
            taskList.markTaskAsDone(taskIndex);
        } catch (IndexOutOfBoundsException e) {
            throw new InvalidTaskIndexException(taskIndex);
        }
        ui.showTaskDone(taskList.getTask(taskIndex));
        storage.save(taskList);
    }
}