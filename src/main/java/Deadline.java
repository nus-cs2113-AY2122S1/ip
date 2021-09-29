/**
 * This class deals with operations on Deadline tasks.
 */
public class Deadline extends Task {
    protected String date;

    /**
     * Constructor of Deadline class.
     *
     * @param description Description of the Deadline task
     * @param date Due date of the Deadline task
     */
    public Deadline(String description, String date) {
        super(description);
        this.date = date;
    }

    /**
     * Returns the date of the Deadline task.
     *
     * @return date of the Deadline
     */
    public String getDate() {
        return date;
    }

    /**
     * Returns the type indicator of the Deadline task.
     *
     * @return type indicator of the Deadline
     */
    public String getType() {
        return "D";
    }

    @Override
    /**
     * Returns the done status, type, description, and date of the Deadline task in String format.
     *
     * @return type, description, date of Deadline
     */
    public String toString() {
        return " [D]" + super.toString() + " (by: " + date + ")";
    }
}
