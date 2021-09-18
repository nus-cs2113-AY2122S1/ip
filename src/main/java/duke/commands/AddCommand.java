package duke.commands;

import duke.exceptions.DukeException;
import duke.exceptions.InvalidTaskTypeException;
import duke.task.Task;
import duke.task.ToDo;
import duke.task.Event;
import duke.task.Deadline;
import duke.Storage;
import duke.TasksList;
import duke.Ui;


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

    /**
     * Adds the task of the specified taskType to the <code>TaskList</code>
     * @param taskList The <code>TaskList</code> which new task will be added to.
     * @param ui The <code>Ui</code> to print out what has been added to the user.
     * @param storage The <code>Storage</code> which helps to save the resultant tasks to the data storage.
     * @throws DukeException If the task type cannot be recognised.
     */
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
