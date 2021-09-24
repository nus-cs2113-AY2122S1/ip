package command;

import exception.AustinException;
import storage.Storage;
import task.TaskList;
import ui.Ui;

import java.io.IOException;

/** Marks the task as "not done". */
public class MarkAsNotDoneCommand extends Command {
    public static final String COMMAND_KEYWORD = "undo";
    private int taskIndex;

    public MarkAsNotDoneCommand(int taskIndex) {
        this.taskIndex = taskIndex;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws AustinException,
            IOException {
        tasks.markAsNotDone(taskIndex);
        storage.updateFile(tasks);
        ui.acknowledgeUndo(taskIndex);
    }
}
