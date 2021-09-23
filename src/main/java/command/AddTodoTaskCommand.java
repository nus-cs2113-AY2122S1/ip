package command;

import storage.Storage;
import task.TaskList;
import ui.Ui;

import java.io.IOException;

public class AddTodoTaskCommand extends Command {
    public static final String COMMAND_KEYWORD = "todo";
    private String description;

    public AddTodoTaskCommand(String description) {
        this.description = description;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws IOException {
        tasks.addTodoTask(description);
        storage.updateFile(tasks);
        ui.acknowledgeAdd();
    }
}
