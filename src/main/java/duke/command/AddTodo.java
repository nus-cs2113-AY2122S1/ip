package duke.command;

import duke.task.Task;
import duke.task.TaskManager;
import duke.task.Todo;
import duke.ui.Ui;

public class AddTodo extends Command {
    private final String description;

    public AddTodo (String description) {
        this.description = description;
    }

    public void execute(TaskManager taskManager, Ui ui) {
        Task addedTask = new Todo(description);
        taskManager.addTask(addedTask);
        ui.printAddTask(addedTask, taskManager);
    }
}
