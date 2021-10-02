import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Class to contain all DEADLINE type tasks, inherits the TASK class.
 */
public class Deadline extends Task {
    protected LocalDate by;

    public Deadline(String description, LocalDate by) {
        super(description);
        setBy(by);
    }

    /**
     * Sets the deadline date of the object.
     *
     * @param newDate new date the object is due by.
     */
    public void setBy(LocalDate newDate) {
        this.by = newDate;
    }

    /**
     * Overrides the getType() method, returning the string "D" representing DEADLINE tasks when called.
     *
     * @return string "D" representing DEADLINE tasks.
     */
    @Override
    public String getType() {
        return "D";
    }

    /**
     * Overrides the getWhen() method, returning the formatted due date of the DEADLINE object.
     *
     * @return the due date of the DEADLINE object in the DATE_FORMAT set in Task class.
     */
    @Override
    public String getFormattedWhen() {
        return this.by.format(DateTimeFormatter.ofPattern((Task.DATE_FORMAT)));
    }

    /**
     * Gets the unformatted date of when the task is due/occurring.
     *
     * @return the unformatted due/occurring date of the Deadline.
     */
    public String getUnformattedWhen() {
        return by.toString();
    }

    /**
     * Overrides the toString() method, returning the
     * type of class "D", the status of the object (done/not done), the object's description and the object's
     * due date.
     *
     * @return string "D" representing DEADLINE tasks, the status of the object, the object's description and the object's due date.
     */
    @Override
    public String toString() {
        return ("[D]" + "[" + getStatusIcon() + "] " + description + "(by: " + by.format(DateTimeFormatter.ofPattern((Task.DATE_FORMAT))) + ")");
    }
}
