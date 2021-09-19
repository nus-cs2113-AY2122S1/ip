package tan.tasktype;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {

    private final String deadlineIcon = "D";
    private LocalDate dateTime;

    public Deadline(String description, LocalDate dateTime) {
        super(description);
        this.dateTime = dateTime;
    }

    public Deadline(String description, boolean isDone, LocalDate dateTime) {
        super(description, isDone);
        this.dateTime = dateTime;
    }

    /**
     * Returns the Task's type in String.
     *
     * @return Returns the string "Deadline".
     */
    @Override
    public String getTaskType() {
        return "Deadline";
    }

    /**
     * Returns the icon for Deadline tasks.
     *
     * @return Returns the String "D"
     */
    @Override
    public String getIcon() {
        return deadlineIcon;
    }

    /**
     * Returns the deadline for the task
     * in String.
     *
     * @return Returns deadline in String.
     */
    @Override
    public String getDateTimeInString() {
        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("d MMM yyyy");
        String formattedDate = dateTime.format(dateFormat);
        return formattedDate;
    }

    /**
     * Returns the Date in its original form, in String.
     *
     * @return The Date in string, unformatted.
     */
    @Override
    public String getDateTimeForStorage() {
        return dateTime.toString();
    }

    /**
     * Returns the whole string
     * including its icon, status, task name
     * and deadline to be printed.
     *
     * @return The task icon, status, name and deadline in String.
     */
    @Override
    public String toString() {
        return ("[" + getIcon() + "]" + "[" + getStatusIcon() + "] "
                + getTaskDescription() + " (by: " + getDateTimeInString() + ")");
    }
}
