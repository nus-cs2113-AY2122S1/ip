package duke.commands;

import duke.storage.Storage;
import duke.tasklist.TaskList;
import duke.ui.Ui;

public class ListCommand extends Command {
    public static final String COMMAND_WORD = "list";
    private static final String MESSAGE_TASK_LIST = "Here are the tasks in your list:\n" + "%1$s";

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        // Returns the list of tasks (numbered) together with their status icons
        String[] formattedTasks = new String[tasks.getSize()];
        for (int i = 0; i < tasks.getSize(); i++) {
            formattedTasks[i] = String.format("%d.%s", i + 1, tasks.getTask(i));
        }
        String taskListOutput = String.join("\n", formattedTasks);
        return String.format(MESSAGE_TASK_LIST, taskListOutput);
    }
}
