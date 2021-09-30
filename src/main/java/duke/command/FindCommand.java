package duke.command;

import duke.data.task.TaskList;
import duke.message.Messages;
import duke.storage.Storage;
import duke.ui.Ui;

public class FindCommand extends Command {

    private final String keyword;

    /**
     * Initialises a new instance.
     *
     * @param keyword The keyword for finding
     */
    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    /**
     * Search for the keyword within the task list and print a list of matching tasks
     *
     * @param tasks   The list of tasks known
     * @param storage The storage that is used
     * @param ui      The ui that is used
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        TaskList matchingTasks = tasks.search(this.keyword);
        if (matchingTasks.isEmpty()) {
            System.out.println("No matching tasks.");
        } else {
            System.out.printf((Messages.MESSAGE_FIND) + "%n", matchingTasks);
        }
    }
}
