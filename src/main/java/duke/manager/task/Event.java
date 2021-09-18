package duke.manager.task;

/**
 * <h1>Event</h1>
 * This class is a child of <code>Task</code>. It occurs at a specific date and time. As such, an
 * <code>Event</code> object additionally contains a String <code>at</code> to represent when the event occurs at.
 */
public class Event extends Task {

    protected String at;

    public Event(String description, String at) {
        super(description);
        if (at.equals("") || at.equals("???")) {
            this.at = "???";
        } else {
            this.at = at;
        }
    }

    public String getAt() {
        return at;
    }

    public void setAt(String at) {
        this.at = at;
    }

    /**
     * Returns the event description with its status in a more reader friendly manner
     */
    @Override
    public String getTaskDescriptionWithStatus() {
        return "[E]" + super.getTaskDescriptionWithStatus() + " (at: " + at + ")";
    }
}
