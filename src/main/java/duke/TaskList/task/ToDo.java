package duke.TaskList.task;

import duke.TaskList.task.Task;
import duke.Ui.DisplayManager;

public class ToDo extends Task {

    public ToDo(String description) {
        super(description);
        this.taskType = "T";
    }

    public String toString() {
        return DisplayManager.createListBox(this.taskType, this.getStatusIcon()) + " " + super.toString();
    }

    public String toDataFormat() {
        return super.toDataFormat();
    }
}
