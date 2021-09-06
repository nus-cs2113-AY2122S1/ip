package duke.task;

import duke.command.DisplayManager;

public class Deadline extends Task{

    private String dateTime;

    public Deadline(String description, String dateTime) {
        super(description);
        this.taskType = "D";
        this.dateTime = dateTime;
    }

    public String toString() {
        return DisplayManager.createListBox(this.taskType, this.getStatusIcon()) + " " + super.toString() + " (by: " + this.dateTime + ")";
    }
}
