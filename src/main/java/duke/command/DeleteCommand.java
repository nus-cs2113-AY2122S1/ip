package duke.command;

import duke.ui.Ui;
import duke.exception.DukeException;
import duke.storage.TaskList;

public class DeleteCommand extends Command {

    private final int taskIndex;

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
    public void execute() throws DukeException {
        Ui.printWithLine(TASK_DELETED + SPACE + SPACE + taskList.getTask(taskIndex) + NEW_LINE +
                CURRENT_MESSAGE + (taskList.size() - 1) + TASK_LEFT_MESSAGE + NEW_LINE);
        taskList.deleteTask(taskIndex);

    }
}
