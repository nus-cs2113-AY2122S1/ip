package duke.command;

import duke.ui.Ui;
import duke.exception.DukeException;
import duke.storage.TaskList;

public class CompleteCommand extends Command {

    private int taskIndex;

    /**
     * A constructor to mark task in taskList as done.
     *
     * @param taskList user's task list.
     * @param ui the user interface.
     * @param taskIndex index of the task to mark as done.
     */
    public CompleteCommand(TaskList taskList, Ui ui, int taskIndex) {
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
        taskList.markTaskDone(taskIndex);
        Ui.printWithLine(TASK_MARKED + "  " + taskList.getTask(taskIndex) + "\n");
    }
}
