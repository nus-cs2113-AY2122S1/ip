package duke.command;

import duke.data.TaskList;
import duke.Ui.Ui;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

/**
 * Command to list tasks with a valid date.
 *  * A <code>Date</code> command can be called with the prefix 'date' in Duke.
 */
public class DateCommand extends Command {
    public DateCommand() {
        super(CommandPrefix.DATE);
    }

    @Override
    public void saveListAndPrintDone(TaskList tasks) {
        super.saveListAndPrintDone(tasks);
        System.out.println("getting date!");
    }

    /**
     * Prints tasks found that have the same valid date input
     *  note that this logically applies to events/ deadlines rather than todo
     * @param tasks TaskList to be read
     */
    @Override
    public void execute(TaskList tasks) {
        LocalDate dateGiven = null;
        try {
            dateGiven = Ui.getDate();
        } catch (DateTimeParseException e) {
            System.out.println("Please enter a valid date!");
            Ui.printDateFormat();
        }
            tasks.printTasksWithGivenDate(dateGiven);
            saveListAndPrintDone(tasks);
    }
}
