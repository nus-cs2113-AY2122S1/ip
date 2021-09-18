package duke.Commands;

import duke.Exceptions.DukeException;
import duke.Exceptions.InvalidTaskTypeException;
import duke.task.Task;
import duke.Storage;
import duke.TasksList;
import duke.Ui;
import duke.task.ToDo;
import duke.task.Event;
import duke.task.Deadline;


public class AddCommand extends Command {
    private final String taskType;
    private final String details;
    private String time;
    public AddCommand(String taskType, String details) {
        this.taskType = taskType;
        this.details = details;
    }

    public AddCommand(String taskType, String details, String time) {
        this.taskType = taskType;
        this.details = details;
        this.time = time;
    }

    public void execute(TasksList taskList, Ui ui, Storage storage) throws DukeException {
        Task task;
        switch (taskType) {
        case "todo":
            task = new ToDo(details);
            break;
        case "event":
            task = new Event(details, time);
            break;
        case "deadline":
            task = new Deadline(details, time);
            break;
        default:
            throw new InvalidTaskTypeException(taskType);
        }
        taskList.addTask(task);
        ui.showTaskAdded(task, taskList.getSize());
        storage.save(taskList);
    }
}
