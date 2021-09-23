package duke.command;

import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

public class DateCommand extends Command {
    private final String date;

    public DateCommand(String date) {
        this.date = date;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        ui.printTaskWithDate(taskList, date);
    }
}
