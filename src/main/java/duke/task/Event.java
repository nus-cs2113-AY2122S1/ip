package duke.task;

public class Event extends Task {

    private String at;

    public Event(String task, String at) {
        super(task);
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
