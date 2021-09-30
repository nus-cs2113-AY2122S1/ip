package Duke.commands;

import Duke.Storage;
import Duke.TaskList;
import Duke.Ui;

import java.time.LocalDateTime;

public class DateCommand extends Command {
    private LocalDateTime dateTime;

    public DateCommand(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.showListForDate(dateTime, ui);
    }
}
