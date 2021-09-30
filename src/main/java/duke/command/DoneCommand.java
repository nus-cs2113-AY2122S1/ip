package duke.command;

import duke.data.exception.OutOfRangeException;
import duke.data.task.Task;
import duke.data.task.TaskList;
import duke.message.Messages;
import duke.storage.Storage;
import duke.ui.Ui;

public class DoneCommand extends Command {
    private final int Index;

    public DoneCommand(int Index) {
        this.Index = Index;
    }

    /**
     * Mark a task as done using index to direct it from tasklist
     *
     * @param tasks   The list of tasks known
     * @param storage The storage that is used
     * @param ui      The ui that is used
     */
    public void execute(TaskList tasks, Ui ui, Storage storage) throws OutOfRangeException {
        if (Index > tasks.size()) {
            throw new OutOfRangeException("in Done task");
        } else {
            Task task = tasks.getTask(Index);
            tasks.getTask(Index).markAsDone();
            storage.changeToFile(tasks);
            System.out.printf((Messages.MESSAGE_DONE) + "%n", task);

        }
    }
}
