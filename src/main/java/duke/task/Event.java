package duke.task;

public class Event extends Task {

    protected String at;
    protected String type = "E";

    public Event(String description, String at) {
        super(description);
        this.at = at;
    }

    public String getTime() {
        return this.at;
    }

    public String getType() {
        return type;
    }

    public String toString() {
        return "[E]" + super.toString() + " (at: " + at + ")";
    }
}