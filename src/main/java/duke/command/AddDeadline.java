package duke.command;

import duke.task.Deadline;
import duke.task.Task;
import duke.task.TaskManager;
import duke.ui.Ui;

public class AddDeadline extends Command {
    private final String description;
    private final String timeDescription;

    public AddDeadline(String[] details) {
        this.description = details[0];
        this.timeDescription = details[1];
    }

    public void execute(TaskManager taskManager, Ui ui) {
        Task addedTask = new Deadline(description, timeDescription);
        taskManager.addTask(addedTask);
        ui.printAddTask(addedTask, taskManager);
    }
}
