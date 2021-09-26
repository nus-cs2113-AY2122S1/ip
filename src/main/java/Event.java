public class Event extends Task {

    protected String at;

    public Event(String description, boolean isDone, String at) {
        super(description, isDone);
        this.at = at;
    }

    @Override
    public String getTaskType() {
        return "E";
    }

    @Override
    public String toString() {
        return super.toString() + " [E][" + super.getStatusIcon() + "] " + description + " (at: " + at + ")\n";
    }

    @Override
    public String getDescription() {
        return description + " (at: " + at + ")\n";
    }

}