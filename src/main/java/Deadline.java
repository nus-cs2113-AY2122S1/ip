/**
 * Class to contain all DEADLINE type tasks, inherits the TASK class.
 */
public class Deadline extends Task {
    protected String by;

    public Deadline(String description, String by) {
        super(description);
        setBy(by);
    }

    /**
     * Sets the deadline date of the object.
     *
     * @param newDate string of new date the object is due by.
     */
    public void setBy(String newDate) {
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
     * Overrides the getWhen() method, returning the due date of the DEADLINE object.
     *
     * @return the due date of the DEADLINE object.
     */
    @Override
    public String getWhen() {
        return this.by;
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
        return ("[D]" + "[" + getStatusIcon() + "] " + description + "(by: " + by + ")");
    }
}
