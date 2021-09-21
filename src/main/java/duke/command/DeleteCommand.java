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
    public void execute() throws DukeException {
        Ui.printWithLine(TASK_DELETED + SPACE + SPACE + taskList.getTask(taskIndex) + NEW_LINE +
                CURRENT_MESSAGE + (taskList.size() - 1) + TASK_LEFT_MESSAGE + NEW_LINE);
        taskList.deleteTask(taskIndex);

    }
}
