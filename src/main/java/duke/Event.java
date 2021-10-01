package duke;

public class Event extends Task {

    private final String taskType = "E";
    protected String at;

    public Event(String description, String at) {
        super(description);
        this.at = at;
    }

    public Event(String description, String at, boolean isDone) {
        super(description);
        this.at = at;
        super.setDone(isDone);
    }

    @Override
    public boolean contains(String search) {
        boolean descriptionContains = super.contains(search);
        boolean atContains = at.toLowerCase().contains(search);
        return descriptionContains || atContains;
    }

    @Override
    public String taskString() {
        return this.taskType + " | " + super.getStatusIcon() + " | " + super.description + " | " + this.at;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + at + ")";
    }
}
