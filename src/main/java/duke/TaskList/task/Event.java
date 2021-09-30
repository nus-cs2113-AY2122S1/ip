package duke.TaskList.task;

import duke.Ui.DisplayManager;

/**
 * Class of task with type 'Event'.
 */
public class Event extends Task {

    private String dateTime;

    /**
     * Creates a new 'Event' type task, sets the description and dateTime of the task.
     * @param description String of task name.
     * @param dateTime String containing time or date of event.
     */
    public Event(String description, String dateTime) {
        super(description);
        this.taskType = "E";
        this.dateTime = dateTime;
    }

    /**
     * Creates and returns the format used to display the task to the user.
     * @return String data used for displaying to user.
     */
    public String toString() {
        return DisplayManager.createListBox(this.taskType, this.getStatusIcon()) + " " + super.toString() + " (at: " + this.dateTime + ")";
    }

    /**
     * Creates and returns the format used in the storage file.
     * @return String data used in storage file.
     */
    public String toDataFormat() {
        return super.toDataFormat() + " | " + this.dateTime;
    }
}
