package command;

import exception.AustinException;
import storage.Storage;
import task.TaskList;
import ui.Ui;

import java.io.IOException;

public class AddDeadlineTaskCommand extends Command {
    public static final String COMMAND_KEYWORD = "deadline";
    private String description;
    private String by;

    public AddDeadlineTaskCommand(String description, String by) {
        this.description = description;
        this.by = by;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws AustinException, IOException {
        tasks.addDeadlineTask(description, by);
        storage.appendToFile(tasks.getTaskItemInFileFormat
                (tasks.tasksCount() - 1));
        ui.acknowledgeAdd();
    }
}
