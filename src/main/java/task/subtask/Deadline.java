package task.subtask;

import task.Task;
import ui.Display;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {

    public final String DATE_TIME_REGEX = "MMM dd yyyy HH:mm";
    private LocalDateTime time;

    public Deadline(String taskName, LocalDate deadlineDate, LocalTime deadlineTime) {
        super(taskName);
        time = LocalDateTime.of(deadlineDate, deadlineTime);
    }

    public String getTime() {
        return time.format(DateTimeFormatter.ofPattern(DATE_TIME_REGEX));
    }

    @Override
    public String toString() {
        return Display.getTwoCheckboxDisplay(Display.CHECKBOX_DEADLINE_TASK_TYPE, getIsCompleted())
                + " " + super.toString() + " (" + getTime() + ")";
    }
}
