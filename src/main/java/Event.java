/**
 * Represents a task that is added to the list by a user. A Event object contains a description
 * represented by a String and a done status represented by a boolean. This is all inherited
 * from superclass Task.
 * Event object contains date that event is happening at, represented by a String.
 */
public class Event extends Task {

    protected String by;

    public Event(String description, String by) {
        super(description);
        this.by = by;
    }

    @Override
    public String toString() {
        return "  [E]" + super.toString() + " (at: " + by + ")";
    }
}