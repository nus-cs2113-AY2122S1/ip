package task.subtask;

import task.Task;
import ui.Display;

/**
 * Represents a 'deadline' type task.
 */
public class Deadline extends Task {

    private String deadlineTime;

    /**
     * Creates a new 'deadline' type task.
     *
     * @param taskName Task name provided by user.
     * @param deadline Time of event provided by user.
     */
    public Deadline(String taskName, String deadline) {
        super(taskName);
        deadlineTime = deadline;
    }

    public String getDeadlineTime() {
        return deadlineTime;
    }

    /**
     * Displays to the user the status, name and time of the 'deadline' type task.
     *
     * @return String representation of 'event' type task to be displayed to user.
     */
    @Override
    public String toString() {
        return Display.getTwoCheckboxDisplay(Display.CHECKBOX_DEADLINE_TASK_TYPE, getIsCompleted())
                + " " + super.toString() + " (" + getDeadlineTime() + ")";
    }
}
