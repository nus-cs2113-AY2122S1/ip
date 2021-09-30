package duke.command;

import duke.data.task.TaskList;
import duke.storage.Storage;
import duke.ui.Ui;
import duke.message.Messages;

public class ListCommand extends Command {
    /**
     * Show a list of all tasks in the tasklist
     *
     * @param tasks   The list of tasks known
     * @param storage The storage that is used
     * @param ui      The ui that is used
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        if (tasks.size() == 0) {
            System.out.println("No task added yet!");
        } else {
            System.out.printf((Messages.MESSAGE_LIST) + "%n", tasks);
        }
    }

}
