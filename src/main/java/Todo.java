/**
 * This class deals with operations on Todo tasks.
 */
public class Todo extends Task {

    /**
     * Constructor of Todo class.
     *
     * @param description Description of the Todo task
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Returns the type indicator of the Todo task.
     *
     * @return type indicator of the Todo
     */
    public String getType() {
        return "T";
    }

    /**
     * Returns the date of the Todo task.
     *
     * @return date of the Todo
     */
    public String getDate() {
        return null;
    }

    @Override
    /**
     * Returns the done status, type, and description of the Todo task in String format.
     *
     * @return type, description, date of Deadline
     */
    public String toString() {
        return " [T]" + super.toString();
    }
}
