/**
 * Represents a task that is added to the list by a user. A Deadline object contains a description
 * represented by a String and a done status represented by a boolean. This is all inherited
 * from superclass Task.
 * Deadline object contains date that task is to be completed by, represented by a String.
 * Inherits all methods from superclass Task.
 */
public class Deadline extends Task {

    protected String by;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    @Override
    public String toString() {
        return "  [D]" + super.toString() + " (by: " + by + ")";
    }
}
