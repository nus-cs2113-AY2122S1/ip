package duke.command;

import duke.ui.Ui;
import duke.exception.DukeException;
import duke.storage.TaskList;

public class DeleteCommand extends Command {

    private int taskIndex;

    /**
     * A constructor to delete a task from taskList.
     *
     * @param taskList user's task list.
     * @param ui the user interface.
     * @param taskIndex index of the task to be removed.
     */
    public DeleteCommand(TaskList taskList, Ui ui, int taskIndex) {
        super(taskList, ui);
        this.taskIndex = taskIndex;
    }

    /**
     * Executes command.
     *
     * @throws DukeException exception thrown when index is out of range of 0 and last index on taskList.
     */
    @Override
    public void executed() throws DukeException {
        Ui.printWithLine(TASK_DELETED + "  " + taskList.getTask(taskIndex) +
                "\nYou currently have " + (taskList.size() - 1) + " left in the list.\n");
        taskList.deleteTask(taskIndex);

    }
}
