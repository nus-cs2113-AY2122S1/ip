package duke.task;

/**
 * This class is used for tasks without any date/time attached to it.
 * E.g: visit new theme park
 */
public class Todo extends Task {


    public Todo(String description) {
        super(description);
    }

    /**
     * Returns Todo task formatted for application, in the form "[T][ ] description"
     *
     * @return Formatted Todo task string for application
     */
    @Override
    public String toString() {
        return TODO_LOGO + super.toString();
    }

    /**
     * Returns Todo task formatted for data file in the form "T | 1/0 | description"
     *
     * @return Formatted Todo task string for data file
     */
    @Override
    public String toTextFileString() {
        return TODO_ACRONYM + " | " + super.toTextFileString();
    }

}
