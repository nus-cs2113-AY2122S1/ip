package duke.command;

import duke.task.Task;
import duke.task.TaskList;
import duke.task.exception.InvalidTaskIndexException;
import duke.task.exception.TaskListEmptyException;

public class DoneCommand extends Command {

    private int doneTaskIndex;

    public DoneCommand(int doneTaskIndex) {
        super();
        this.doneTaskIndex = doneTaskIndex;
    }

    /**
     * Mark the task specified as done.
     *
     * @param taskList Current task list.
     */
    @Override
    public void execute(TaskList taskList) {
        try {
            taskList.markTaskAsDone(doneTaskIndex);
        } catch (InvalidTaskIndexException | IndexOutOfBoundsException e) {
            ui.printInvalidTaskIndexError();
            return;
        } catch (TaskListEmptyException e) {
            ui.printTaskListEmptyError();
            return;
        }

        Task completedTask;
        try {
            completedTask = taskList.getTask(doneTaskIndex);
        } catch (TaskListEmptyException e) {
            ui.printTaskListEmptyError();
            return;
        } catch (InvalidTaskIndexException e) {
            ui.printInvalidTaskIndexError();
            return;
        }

        ui.printMarkAsDone(completedTask);
        saveTaskList(taskList);
    }
}
