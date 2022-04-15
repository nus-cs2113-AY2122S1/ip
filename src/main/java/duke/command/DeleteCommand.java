package duke.command;

import duke.data.TaskList;

/**
 * Command to delete tasks from both the task list and save file.
 * A <code>Delete</code> command can be called with the prefix 'delete' in Duke.
 */
public class DeleteCommand extends Command {
    public DeleteCommand() {
        super(CommandPrefix.DELETE);
    }

    @Override
    public void saveListAndPrintDone(TaskList tasks) {
        super.saveListAndPrintDone(tasks);
        System.out.println("deleting tasks!");
    }

    /**
     * Deletes a set of <code>Task</code> from both task list and save file.
     *
     * @param tasks TaskList to delete tasks from.
     */
    @Override
    public void execute(TaskList tasks) {
        tasks.deleteTasks();
        saveListAndPrintDone(tasks);
    }

}
