package unker.task;

public class Event extends Task {

    protected String at;

    public Event(String task, String at) {
        super(task);
        this.at = at;
    }

    /**
     * Gets the time of the event.
     * 
     * @return The time of the event.
     */
    public String getAt() {
        return at;
    }

    @Override
    public String toString() {
        return String.format("[E] %s (at: %s)", super.toString(), this.at);
    }
}
