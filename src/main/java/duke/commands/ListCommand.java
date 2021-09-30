package duke.commands;

import duke.tasklist.TaskList;

/**
 * Lists all tasks in the task list.
 */
public class ListCommand extends Command {
    /** Unique word associated with the command. */
    public static final String COMMAND_WORD = "list";

    private static final String MESSAGE_TASK_LIST = "Here are the tasks in your list:\n" + "%1$s";

    @Override
    public String execute(TaskList tasks) {
        // Returns the list of tasks (numbered) together with their information
        String[] formattedTasks = new String[tasks.getSize()];
        for (int i = 0; i < tasks.getSize(); i++) {
            formattedTasks[i] = String.format("%d.%s", i + 1, tasks.getTask(i));
        }
        String taskListOutput = String.join("\n", formattedTasks);
        return String.format(MESSAGE_TASK_LIST, taskListOutput);
    }
}
