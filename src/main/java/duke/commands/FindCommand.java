package duke.commands;

import java.util.ArrayList;

import duke.task.Task;
import duke.tasklist.TaskList;
import duke.ui.Ui;

/**
 * Finds tasks that match the given keyword.
 */
public class FindCommand extends Command {
    /** Unique word associated with the command. */
    public static final String COMMAND_WORD = "find";

    private static final String MESSAGE_MATCHING_TASK_LIST = "Here are the matching tasks in your list:\n" + "%1$s";

    private final String keyword;

    /**
     * Instantiates command and stores keyword.
     *
     * @param keyword Keyword that will be used to search for a task.
     */
    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    @Override
    public String execute(TaskList tasks) {
        final ArrayList<String> formattedTasks = new ArrayList<>();
        for (int i = 0; i < tasks.getSize(); i++) {
            final Task task = tasks.getTask(i);
            if (task.getDescription().contains(keyword)) {
                formattedTasks.add(Ui.formatTaskForTaskList(i + 1, task));
            }
        }
        String taskListOutput = String.join("\n", formattedTasks);
        return String.format(MESSAGE_MATCHING_TASK_LIST, taskListOutput);
    }
}
