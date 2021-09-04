package task.subtask;

import utils.Display;

public class Event extends Deadline {

    public Event(String taskName, String eventTime) {
        super(taskName, eventTime);
    }

    @Override
    public String toString() {
        return Display.getTwoCheckboxDisplay(Display.CHECKBOX_EVENT_TASK_TYPE, getIsCompleted())
                + " " + getTask() + " (" + getDeadlineTime() + ")";
    }
}
