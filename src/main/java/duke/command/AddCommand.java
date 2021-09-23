package duke.command;

import duke.data.TaskList;

/**
 * Adds tasks to a list.
 * A <code>Add</code> command can be called with the prefix 'add' in Duke.
 */
public class AddCommand extends Command{
    public AddCommand() {
        super(CommandPrefix.ADD);
    }

    @Override
    public void saveListAndPrintDone(TaskList tasks) {
        super.saveListAndPrintDone(tasks);
        System.out.println("adding");
    }

    /**
     * Executes add functionality - adding tasks to a TaskList task, then writes the data to a file.
     * A task can either be a todo, deadline or event
     *
     * Additionally, the user would be informed if a <code>Date</code> is recorded in user input.
     *  For instance, 2020-10-10 would be recognised as Oct 10 2020, and would be recorded as an
     *      additional attribute by the program.
     *  @param tasks TaskList to add tasks to
     *
     */
    @Override
    public void execute(TaskList tasks) {
        tasks.addTasksToList();
        saveListAndPrintDone(tasks);
    }
}
