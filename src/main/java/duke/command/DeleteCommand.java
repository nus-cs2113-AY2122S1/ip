package duke.command;

import duke.task.Task;
import duke.task.TaskList;
import duke.task.exception.InvalidTaskIndexException;
import duke.task.exception.TaskListEmptyException;

public class DeleteCommand extends Command {

    private int deleteTaskIndex;

    public DeleteCommand(int deleteTaskIndex) {
        super();
        this.deleteTaskIndex = deleteTaskIndex;
    }

    /**
     * Deletes the specified task.
     *
     * @param taskList Current task list.
     */
    @Override
    public void execute(TaskList taskList) {
        Task deletedTask;
        try {
            deletedTask = taskList.getTask(deleteTaskIndex);
            taskList.deleteTask(deleteTaskIndex);
        } catch (NumberFormatException | InvalidTaskIndexException | IndexOutOfBoundsException e) {
            ui.printInvalidTaskIndexError();
            return;
        } catch (TaskListEmptyException e) {
            ui.printTaskListEmptyError();
            return;
        }

        ui.printDeletedTask(deletedTask, taskList.getTotalTasks());
        saveTaskList(taskList);
    }
}
