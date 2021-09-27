package duke.task;

import java.time.LocalDate;

public class Event extends Task {
    public static final String EVENT_ICON = "[E]";
    private LocalDate duration;

    public Event(String taskName, LocalDate duration) {
        super(taskName);
        this.duration = duration;
    }

    /**
     * Exports the task as a string in the standard format to be stored in the save file.
     *
     * @return The formatted string with the task data.
     */
    @Override
    public String exportTask() {
        return "E|" + super.getStatus() + "|" + super.toString() + "|" + duration + System.lineSeparator();
    }

    @Override
    public String toString() {
        return EVENT_ICON + super.toString() + " (at: " + duration + ")";
    }
}
