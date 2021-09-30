package duke.commands;

import duke.data.TaskList;
import duke.data.task.Task;
import duke.storage.Storage;
import duke.ui.TextUi;

/**
 * Displays all the tasks in the task list.
 */
public class ListCommand extends Command {
    public static final String COMMAND_WORD = "list";
    public static final String MESSAGE_EMPTY_LIST = "No tasks in the list!";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Displays all the tasks in the task list. "
            + "\n"
            + "Example: "
            + COMMAND_WORD
            + "\n";

    /**
     * Shows the user a list of all the tasks in the task list.
     * If the task list is empty, an empty list message will be shown instead.
     *
     * @param tasks a task list that contains all the tasks
     * @param ui accesses format and messages to show to the user
     * @param storage accesses a text file which stores the task list
     */
    @Override
    public void execute(TaskList tasks, TextUi ui, Storage storage) {
        if (tasks.getSize() == 0) {
            ui.showToUser(MESSAGE_EMPTY_LIST);
        } else {
            showAllTasks(tasks, ui);
        }
    }

    private static void showAllTasks(TaskList tasks, TextUi ui) {
        System.out.println(ui.LINE_PREFIX + "Here are the tasks in your list:");
        for (Task item : tasks.getList()) {
            System.out.println(ui.LINE_PREFIX + (tasks.getIndex(item) + 1) + "." + item);
        }
    }
}