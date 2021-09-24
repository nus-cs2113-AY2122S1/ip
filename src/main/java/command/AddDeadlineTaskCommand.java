package command;

import exception.AustinException;
import storage.Storage;
import task.TaskList;
import ui.Ui;

import java.io.IOException;
import java.time.LocalDateTime;

/** Adds a deadline task into the list and the file */
public class AddDeadlineTaskCommand extends Command {
    public static final String COMMAND_KEYWORD = "deadline";
    private String description;
    private LocalDateTime by;

    public AddDeadlineTaskCommand(String description, LocalDateTime by) {
        this.description = description;
        this.by = by;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws AustinException, IOException {
        tasks.addDeadlineTask(description, by);
        storage.updateFile(tasks);
        ui.acknowledgeAdd();
    }
}
