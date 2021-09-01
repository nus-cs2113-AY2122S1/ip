/**
 * This class is used for tasks without any date/time attached to it.
 * E.g: visit new theme park
 */
public class Todo extends Task {
    protected static final String TODO_LOGO = "[T]";

    public Todo(String description) {
        super(description);
    }

    /**
     * Returns Todo task formatted in the form "[T][ ] description"
     *
     * @return Formatted Todo task string
     */
    @Override
    public String toString() {
        return TODO_LOGO + super.toString();
    }
}
