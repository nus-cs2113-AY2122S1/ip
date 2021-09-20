package duke.command;

import duke.ui.Ui;
import duke.exception.DukeException;
import duke.storage.TaskList;

public class DeleteCommand extends Command {

    private int taskIndex;

    public DeleteCommand(TaskList taskList, Ui ui, int taskIndex) {
        super(taskList, ui);
        this.taskIndex = taskIndex;
    }

    @Override
    public void executed() throws DukeException {
        Ui.printWithLine(TASK_DELETED + "  " + taskList.getTask(taskIndex) +
                "\nYou currently have " + (taskList.size() - 1) + " left in the list.\n");
        taskList.deleteTask(taskIndex);

    }
}
