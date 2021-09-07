package command;

import task.Task;
import task.TaskManager;
import ui.UI;

public class AddCommand extends Command {
    private String taskType;
    private String taskName;
    private String date;

    public AddCommand(String taskType, String taskName, String date) {
        this.taskType = taskType;
        this.taskName = taskName;
        this.date = date;
    }

    public AddCommand(String taskType, String taskName) {
        this(taskType, taskName, "");
    }

    @Override
    public void execute(TaskManager taskManager, UI ui) {
        Task newTask = taskManager.addTask(taskType, taskName, date);
        ui.printTaskAddedMessage(newTask, taskManager.getTaskCount());
    }
}
