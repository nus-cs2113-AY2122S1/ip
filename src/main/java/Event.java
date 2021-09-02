public class Event extends Task {
    protected String by;

    public Event(String description, char taskType, String by) {
        super(description, taskType);
        this.by = by;
    }

    @Override
    public String toString() {
        return " [E][" + getStatusIcon() + "] " + super.toString() + by;
    }
}

