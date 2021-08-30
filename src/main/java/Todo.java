/**
 * The Todo class is a subclass of Task which
 * implements its functionality.
 *
 * @author richwill28
 */
class Todo extends Task {
    /**
     * The constructor method. Initializes the
     * task description.
     *
     * @param description Task description.
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Returns the string representation of Todo.
     *
     * @return The string representation of Todo.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
