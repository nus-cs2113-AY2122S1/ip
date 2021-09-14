package duke.task;

import duke.command.DisplayManager;

public class ToDo extends Task{

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
