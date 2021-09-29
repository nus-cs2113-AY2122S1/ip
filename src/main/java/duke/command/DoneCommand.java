package duke.command;

import duke.data.TaskList;

/**
 * Command to mark tasks as done from both the task list and save file.
 *  A <code>Done</code> command can be called with the prefix 'done' in Duke.
 */
public class DoneCommand extends Command {
    public DoneCommand() {
        super(CommandPrefix.DONE);
    }

    @Override
    public void saveListAndPrintDone(TaskList tasks) {
        super.saveListAndPrintDone(tasks);
        System.out.println("marking as done!");
    }

    /**
     * Mark a set of <code>Task</code> as done in both task list and save file.
     * @param tasks TaskList to mark tasks as done from
     *
     */
    @Override
    public void execute(TaskList tasks) {
        tasks.markTasksAsDone();
        saveListAndPrintDone(tasks);
    }
}
