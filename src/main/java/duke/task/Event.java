package duke.task;


/**
 * One of the three types of tasks creatable by the user.
 * Has an additional at field that indicates when the task starts and ends.
 */
public class Event extends Task {
    protected String at;

    public Event(String description, String at) {
        super(description);
        this.at = at;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + at + ")";
    }

    public String getAt() {
        return at;
    }
}
