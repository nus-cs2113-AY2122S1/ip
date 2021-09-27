package duke.TaskList.task;

import duke.Ui.DisplayManager;

public class Event extends Task {

    private String dateTime;

    public Event(String description, String dateTime) {
        super(description);
        this.taskType = "E";
        this.dateTime = dateTime;
    }

    public String toString() {
        return DisplayManager.createListBox(this.taskType, this.getStatusIcon()) + " " + super.toString() + " (at: " + this.dateTime + ")";
    }

    public String toDataFormat() {
        return super.toDataFormat() + " | " + this.dateTime;
    }
}
