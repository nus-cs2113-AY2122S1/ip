package alfred.command;

import alfred.task.Deadline;
import alfred.task.Event;
import alfred.task.Task;
import alfred.task.Todo;
import alfred.ui.TextUi;

import java.time.LocalDate;

public class AddTaskCommand extends Command {
    private final String taskType;
    private final String taskDescription;
    private final LocalDate taskDate;

    public AddTaskCommand(String taskType, String taskDescription, LocalDate taskDate) {
        this.taskType = taskType;
        this.taskDescription = taskDescription;
        this.taskDate = taskDate;
    }

    /**
     * This method adds a Task to the TaskList based on the given command.
     */
    public void execute() {
        Task addedTask;
        switch (taskType) {
        case "T":
            addedTask = new Todo(taskDescription);
            taskList.addTask(addedTask);
            break;
        case "E":
            addedTask = new Event(taskDescription, taskDate);
            taskList.addTask(addedTask);
            break;
        case "D":
            addedTask = new Deadline(taskDescription, taskDate);
            taskList.addTask(addedTask);
            break;
        default:
            return;
        }
        TextUi.addTaskMessage(addedTask, taskList.getSize());
    }
}
