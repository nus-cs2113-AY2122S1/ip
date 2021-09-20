package duke.task;

public class Event extends Task {
    protected String at;

    public Event(String description, String at) {
        super(description);
        this.at = at;
        super.setDateAndTime(at);
    }

    public String getAt() {
        return at;
    }

    @Override
    public void setDateAndTime(String line) {
        super.setDateAndTime(line);
    }

    @Override
    public String toString() {
        if (hasDateTime) {
            at = getDateAndTime();
        }
        return "[E]" + super.toString() + " (at: " + at + ")";
    }
}
