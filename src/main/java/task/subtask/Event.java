package task.subtask;

import ui.Display;

/**
 * Represents an 'event' type task.
 */
public class Event extends Deadline {

    /**
     * Creates a new 'event' type task.
     *
     * @param taskName Task name provided by user.
     * @param eventTime Time of event provided by user.
     */
    public Event(String taskName, String eventTime) {
        super(taskName, eventTime);
    }

    /**
     * Displays to the user the status, name and time of the 'event' type task.
     *
     * @return String representation of 'event' type task to be displayed to user.
     */
    @Override
    public String toString() {
        return Display.getTwoCheckboxDisplay(Display.CHECKBOX_EVENT_TASK_TYPE, getIsCompleted())
                + " " + getTask() + " (" + getDeadlineTime() + ")";
    }
}
