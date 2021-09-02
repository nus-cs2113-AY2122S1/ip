public class Event extends Task{
    protected String on;

    public String getOn() {
        return on;
    }

    public void setOn(String on) {
        this.on = on;
    }

    public Event(String description, String on) {
        super(description);
        this.on = on;
    }

    public String toString() {
        return ("[E][" + getStatusIcon() + "] " + description + " (at: " + on + ")");
    }
}
