package Task;

public class Event extends Task {
    /** The deadline of the task**/
    protected String at;

    /**
     * A constructor of Event.
     * @param content the name of the task.
     * @param at the deadline of the task.
     */
    public Event(String content, String at) {
        super(content);
        this.at = at;
    }

    /**
     * A methods to return the whole information of the Event
     * @return A String contains the type, name, and deadline for this task.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + "（a" + at + ")";
    }
}
