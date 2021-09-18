package task.subtask;

import ui.Display;

import java.time.LocalDate;
import java.time.LocalTime;

public class Event extends Deadline {

    public Event(String taskName, LocalDate eventDate, LocalTime eventTime) {
        super(taskName, eventDate, eventTime);
    }

    @Override
    public String toString() {
        return Display.getTwoCheckboxDisplay(Display.CHECKBOX_EVENT_TASK_TYPE, getIsCompleted())
                + " " + getTask() + " (" + getTime() + ")";
    }
}
