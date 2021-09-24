package command;

import exception.AustinException;
import storage.Storage;
import task.TaskList;
import ui.Ui;

import java.io.IOException;
import java.time.LocalDateTime;

public class AddEventTaskCommand extends Command {
    public static final String COMMAND_KEYWORD = "event";
    private String description;
    private LocalDateTime at;

    public AddEventTaskCommand(String description, LocalDateTime at) {
        this.description = description;
        this.at = at;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws AustinException,
            IOException {
        tasks.addEventTask(description, at);
        storage.updateFile(tasks);
        ui.acknowledgeAdd();
    }
}
