package duke.command;

import duke.data.TaskList;

/**
 * Command to erase tasks from both the task list and save file.
 *  * A <code>Clear</code> command can be called with the prefix 'clear' in Duke.
 */
public class ClearCommand extends Command {
    public ClearCommand() {
        super(CommandPrefix.CLEAR);
    }

    @Override
    public void saveListAndPrintDone(TaskList tasks) {
        super.saveListAndPrintDone(tasks);
        System.out.println("clearing list!");
    }

    /**
     * Removes all tasks in TaskList and save file, regardless of done status.
     *  @param tasks TaskList to remove all tasks from
     *
     */
    @Override
    public void execute(TaskList tasks) {
        tasks.clearTaskList();
        saveListAndPrintDone(tasks);
    }
}
