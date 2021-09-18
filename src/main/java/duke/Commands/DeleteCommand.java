package duke.Commands;

import duke.Exceptions.DukeException;
import duke.Exceptions.InvalidTaskIndexException;
import duke.Storage;
import duke.TasksList;
import duke.Ui;
import duke.task.Task;


public class DeleteCommand extends Command{
    int taskIndex;

    public DeleteCommand(int taskIndex) {
        this.taskIndex = taskIndex;
    }

    public void execute(TasksList taskList, Ui ui, Storage storage) throws DukeException {
        Task task;
        try {
            task = taskList.getTask(taskIndex);
            taskList.deleteTask(taskIndex);
        } catch (IndexOutOfBoundsException e) {
            throw new InvalidTaskIndexException(taskIndex);
        }
        ui.showTaskDeleted(task, taskList.getSize());
        storage.save(taskList);
    }
}