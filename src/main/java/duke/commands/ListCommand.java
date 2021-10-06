package duke.commands;

import duke.components.Storage;
import duke.components.TaskList;
import duke.components.Ui;

import static duke.constants.Messages.HORIZONTAL_LINE;
import static duke.constants.Messages.MESSAGE_NO_TASK;
import static duke.constants.Messages.LINE_PREFIX;
import static duke.constants.Messages.MESSAGE_LISTING_TASKS;

/**
 * A representation of the command for listing the tasks.
 */
public class ListCommand extends Command {

    /**
     * Executes the list command.
     *
     * @param tasks The ArrayList of tasks.
     * @param ui The component of Duke that deals with the interaction with the user.
     * @param storage The component of Duke that deals with loading tasks from the file and saving tasks in the file.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        System.out.println(HORIZONTAL_LINE);
        if (tasks.getNumberOfTasks() <= 0) {
            System.out.println(MESSAGE_NO_TASK);
            return;
        }
        System.out.println(MESSAGE_LISTING_TASKS);
        for (int i = 0; i < tasks.getNumberOfTasks(); i++) {
            System.out.println(LINE_PREFIX + (i + 1) + ". " + tasks.getTaskByIndex(i));
        }
    }
}
