package duke.command;

import duke.data.exception.OutOfRangeException;
import duke.data.task.Task;
import duke.data.task.TaskList;
import duke.message.Messages;
import duke.storage.Storage;
import duke.ui.Ui;

public class DeleteCommand extends Command {
    private final int index;

    public DeleteCommand(int Index) {
        this.index = Index;
    }

    /**
     * Delete a task from the tasklist using index
     *
     * @param tasks   The list of tasks known
     * @param storage The storage that is used
     * @param ui      The ui that is used
     */
    public void execute(TaskList tasks, Ui ui, Storage storage) throws OutOfRangeException {
        if (index > tasks.size()) {
            throw new OutOfRangeException("in Delete task");
        } else {
            Task task = tasks.deleteTask(index);
            System.out.printf((Messages.MESSAGE_DELETE) + "%n", task);
            System.out.printf((Messages.MESSAGE_COUNT) + "%n", tasks.size());
            storage.changeToFile(tasks);
        }
    }
}
