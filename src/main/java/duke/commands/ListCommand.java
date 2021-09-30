package duke.commands;

import duke.data.TaskList;
import duke.data.task.Task;
import duke.storage.Storage;
import duke.ui.TextUi;

public class ListCommand extends Command {
    public static final String COMMAND_WORD = "list";
    public static final String MESSAGE_EMPTY_LIST = "No tasks in the list!";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Displays all the tasks in the task list. "
            + "\n|| "
            + "Example: "
            + COMMAND_WORD
            + "\n||";

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