package Duke.Task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Event extends Task {

    protected String by;

    public Event(String description, String by) {
        super(description);
        this.by = by;
    }

    /**
     * Returns a single string of the event task
     * with its icon, status, task name, preposition used and date and time of the event.
     *
     * @return The icon, status, task name, preposition used and date and time of the event, all in a single string.
     */
    @Override
    public String toString() {
        //get the preposition used
        int spaceIndex = by.indexOf(' ');
        String preposition = by.substring(0, spaceIndex);
        String dueDate = by.substring(spaceIndex + 1);

        String[] splittedDueDate = dueDate.split(" ");
        LocalDate date = LocalDate.parse(splittedDueDate[0], DateTimeFormatter.ofPattern("dd-MM-yyyy"));
        String time = getTime(splittedDueDate);
        //output message
        return "[E]" + super.getStatusIcon() + super.toString() + " (" + preposition + ": " + date.format(DateTimeFormatter.ofPattern("d MMM yyyy")) + time + ")";
    }

    /**
     * Get the time from the due date entered by the user.
     *
     * @param splittedDueDate The separated due date string, splitted into an array of strings.
     * @return Returns the time in a String if a time is entered by the user, else return "".
     */
    private String getTime(String[] splittedDueDate) {
        if (splittedDueDate.length > 1) {
            return " " + splittedDueDate[1];
        }
        return "";
    }

    /**
     * Returns the task name followed by a slash and its due date after.
     *
     * @return The task name followed by a slash and then, its due date, in a single string.
     */
    @Override
    public String getDescription() {
        return description + " /" + by;
    }
}
