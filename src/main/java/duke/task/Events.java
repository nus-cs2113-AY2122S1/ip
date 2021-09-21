package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Events extends Task {

    private final LocalDate eventDate;

    /**
     * A constructor to create a task of type event.
     *
     * @param taskName name of task.
     * @param eventDate date of occurrence.
     */
    public Events(String taskName, LocalDate eventDate) {
        super(taskName);
        this.eventDate = eventDate;
    }

    /**
     * Formats and return the formatted date of task as string.
     *
     * @return String of formatted date.
     */
    @Override
    public String getDate() {
        return eventDate.format(DateTimeFormatter.ofPattern(DATE_FORMAT));
    }

    /**
     * Returns a String format of task to be printed.
     *
     * @return String of type, isDone, task name and date.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + getDate() + ")";
    }

    /**
     * Returns a String format of task to be stored in storage text file.
     *
     * @return String of type, isDone, task name and date.
     */
    @Override
    public String storageText () {
        return EVENT_E + super.storageText() + "|" + getDate();
    }
}
