package duke.command;

import duke.TaskList;
import duke.TaskType;
import duke.storage.Storage;
import duke.ui.Ui;

import java.time.format.DateTimeParseException;

public class AddCommand extends Command {
    private final TaskType specificTask;
    private final String fullCommand;

    public AddCommand(String fullCommand, TaskType specificTask) {
        this.specificTask = specificTask;
        this.fullCommand = fullCommand;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DateTimeParseException {
        tasks.addTask(fullCommand, specificTask, ui);
    }
}
