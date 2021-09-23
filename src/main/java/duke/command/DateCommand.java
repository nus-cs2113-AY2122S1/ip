package duke.command;

import duke.data.TaskList;

/**
 * Command to list tasks with a valid date.
 *  * A <code>Date</code> command can be called with the prefix 'date' in Duke.
 */
public class DateCommand extends Command{
    public DateCommand() {
        super(CommandPrefix.DATE);
    }

    @Override
    public void saveListAndPrintDone(TaskList tasks) {
        super.saveListAndPrintDone(tasks);
        System.out.println("getting date!");
    }

    //assume date is given, if not don't print

    /**
     * Prints tasks with a valid date
     * @param tasks TaskList to be read
     *
     */
    @Override
    public void execute(TaskList tasks) {
        tasks.printTasksWithValidDate();
        saveListAndPrintDone(tasks);
    }
}
