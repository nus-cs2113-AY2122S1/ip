package duke.task;

/**
 * Class that encapsulates an Event task
 */
public class Event extends Task {
    protected String at;

    public Event(String description, String at) {
        super(description);
        this.at = at;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + this.at + ")";
    }

    @Override
    public String parseDataIntoString() {
        return "E" + super.parseDataIntoString() + " | " + this.at;
    }
}
