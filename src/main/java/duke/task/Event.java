package duke.task;

public class Event extends Task {

    private String at;

    public Event(String description, String at) {
        this(description, at, false);
    }

    public Event(String description, String at, boolean isDone) {
        super(description, isDone);
        this.at = at;
    }

    @Override
    public String toString() {
        return String.format("[E][%s] %s (at: %s)", getStatusIcon(), this.description, this.at);
    }

    @Override
    public String toSave() {
        return String.format("event | %s | %b | %s", this.description, this.isDone, this.at);
    }
}
