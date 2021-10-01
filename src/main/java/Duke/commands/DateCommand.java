package Duke.commands;

import Duke.Storage;
import Duke.TaskList;
import Duke.Ui;

import java.time.LocalDateTime;

public class DateCommand extends Command {
    private LocalDateTime dateTime;

    /**
     * Class constructor for DateCommand
     *
     * @param dateTime Date and time to find the deadlines before
     */
    public DateCommand(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    /**
     * Shows list of deadlines that occur before dateTime
     *
     * @param tasks TaskList containing Task
     * @param ui User interface
     * @param storage File management system
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.showListForDate(dateTime, ui);
    }
}
