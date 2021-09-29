import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Class to contain all EVENT type tasks, inherits the TASK class.
 */
public class Event extends Task {
    protected LocalDate at;

    public Event(String description, LocalDate at) {
        super(description);
        setAt(at);
    }

    /**
     * Sets the EVENT date of the object.
     *
     * @param newDate new date the object is occurring.
     */

    public void setAt(LocalDate newDate) {
        this.at = newDate;
    }

    /**
     * Overrides the getType() method, returning the string "E" representing EVENT tasks when called.
     *
     * @return string "E" representing DEADLINE tasks.
     */
    @Override
    public String getType() {
        return "E";
    }

    /**
     * Overrides the getWhen() method, returning the formatted occurring date of the EVENT object.
     *
     * @return the occurring date of the EVENT object in the DATE_FORMAT set in Task class.
     */
    @Override
    public String getFormattedWhen() {
        return this.at.format(DateTimeFormatter.ofPattern((Task.DATE_FORMAT)));
    }

    /**
     * Gets the unformatted date of when the task is due/occurring.
     *
     * @return the unformatted due/occurring date of the Event.
     */
    public String getUnformattedWhen() {
        return at.toString();
    }

    /**
     * Overrides the toString() method, returning the
     * type of class "E", the status of the object (done/not done), the object's description and the object's
     * occurring date.
     *
     * @return string "E" representing DEADLINE tasks, the status of the object, the object's description and the object's occurring date.
     */
    @Override
    public String toString() {
        return ("[E]" + "[" + getStatusIcon() + "] " + description + "(at: " + at.format(DateTimeFormatter.ofPattern((Task.DATE_FORMAT))) + ")");
    }
}
