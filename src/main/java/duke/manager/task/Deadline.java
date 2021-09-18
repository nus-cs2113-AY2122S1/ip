package duke.manager.task;

/**
 * <h1>Deadline</h1>
 * This class is a child of <code>Task</code>. It is a more specific type of task that needs to be done
 * by a certain date and time. As such, a <code>Deadline</code> object additionally contains a String <code>by</code>
 * to represent when the deadline of the task is.
 */
public class Deadline extends Task {

    protected String by;

    public Deadline(String description, String by) {
        super(description);
        if (by.equals("") || by.equals("???")) {
            this.by = "???";
        } else {
            this.by = by;
        }
    }

    public String getBy() {
        return by;
    }

    public void setBy(String by) {
        this.by = by;
    }

    /**
     * Returns the deadline description with its status in a more reader friendly manner
     */
    @Override
    public String getTaskDescriptionWithStatus() {
        return "[D]" + super.getTaskDescriptionWithStatus() + " (by: " + by + ")";
    }
}
