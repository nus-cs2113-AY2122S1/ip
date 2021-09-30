package duke.TaskList.task;

import duke.TaskList.task.Task;
import duke.Ui.DisplayManager;

/**
 * Class of task with type 'Todo'.
 */
public class ToDo extends Task {

    /**
     * Creates a new 'Todo' type task, sets the description of the task.
     * @param description String of task name.
     */
    public ToDo(String description) {
        super(description);
        this.taskType = "T";
    }

    /**
     * Creates and returns the format used to display the task to the user.
     * @return String data used for displaying to user.
     */
    public String toString() {
        return DisplayManager.createListBox(this.taskType, this.getStatusIcon()) + " " + super.toString();
    }

    /**
     * Creates and returns the format used in the storage file.
     * @return String data used in storage file.
     */
    public String toDataFormat() {
        return super.toDataFormat();
    }
}
