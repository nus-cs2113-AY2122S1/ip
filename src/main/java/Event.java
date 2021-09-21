/**
 * Class to contain all EVENT type tasks, inherits the TASK class.
 */
public class Event extends Task {
    protected String at;

    public Event(String description, String at) {
        super(description);
        setAt(at);
    }

    /**
     * Sets the EVENT date of the object.
     *
     * @param newDate string of new date the object is occurring.
     */
    public void setAt(String newDate) {
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
     * Overrides the getWhen() method, returning the occurring date of the EVENT object.
     *
     * @return the occurring date of the EVENT object.
     */
    @Override
    public String getWhen() {
        return this.at;
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
        return ("[E]" + "[" + getStatusIcon() + "] " + description + "(at: " + at + ")");
    }
}
