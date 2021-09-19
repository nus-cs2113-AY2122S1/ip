package tan.tasktype;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Event extends Task {

    private LocalDate dateTime;
    private final String eventIcon = "E";

    public Event(String description, LocalDate dateTime) {
        super(description);
        this.dateTime = dateTime;
    }

    public Event(String description, Boolean isDone, LocalDate dateTime) {
        super(description, isDone);
        this.dateTime = dateTime;
    }

    /**
     * Returns the Task's type in String.
     *
     * @return Returns the string "Event".
     */
    @Override
    public String getTaskType() {
        return "Event";
    }

    /**
     * Returns the icon for Event tasks.
     *
     * @return Returns the String "E"
     */
    @Override
    public String getIcon() {
        return eventIcon;
    }

    /**
     * Returns the do-by date for the task
     * in String.
     *
     * @return Returns do-by date in String.
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
     * Returns the formatted string to print the task nicely.
     * This will return the whole string
     * including its icon, status, task name
     * and do-by date.
     *
     * @return The task icon, status, name and do-by date all in a String.
     */
    @Override
    public String toString() {
        return ("[" + getIcon() + "]" + "[" + getStatusIcon() + "] "
                + getTaskDescription() + " (at: " + getDateTimeInString() + ")");
    }
}
