package duke.command;

import duke.ui.Ui;
import duke.exception.DukeException;
import duke.storage.TaskList;

public class CompleteCommand extends Command {

    private int taskIndex;
    public CompleteCommand(TaskList taskList, Ui ui, int taskIndex) {
        super(taskList, ui);
        this.taskIndex = taskIndex;
    }

    @Override
    public void executed() throws DukeException {
        taskList.markTaskDone(taskIndex);
        Ui.printWithLine(TASK_MARKED + "  " + taskList.getTask(taskIndex) + "\n");
    }
}
