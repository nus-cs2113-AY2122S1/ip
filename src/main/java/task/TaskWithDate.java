package task;

import java.time.LocalDateTime;

import static ui.Ui.DATE_TIME_FORMATTER;

/**
 * Represents the abstract class for tasks that have a date parameter.
 */
public abstract class TaskWithDate extends Task{
    protected LocalDateTime dateTime;

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public TaskWithDate(String description, boolean isDone, LocalDateTime dateTime) {
        super(description, isDone);
        this.dateTime = dateTime;
    }

    @Override
    public String toString() {
        return getStatusIcon() + " " + super.toString() + " (" + DATE_TIME_FORMATTER.format(dateTime) + ")";
    }

    /**
     * Checks if one taskWithDate is after the other based on the date.
     * @param taskWithDate to compare
     * @return task with a later date
     */
    public boolean isAfter(TaskWithDate taskWithDate) {
        return this.dateTime.isAfter(taskWithDate.getDateTime());
    }

    @Override
    public String toFileString() {
        return super.toFileString() + "|" + dateTime;
    }
}
