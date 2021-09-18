package duke.data.task;

public class Event extends Task {

    protected String at;

    public Event(String description, String at) {
        super(description);
        this.at = at;
    }


    @Override
    public String getType() {
        return "E";
    }

    public String toString() {
        return "[E]" + super.toString() + " (at: " + at + ")";
    }

    public String store() {
        return "E | " + (isDone ? 1 : 0) + " | " +
                super.getDescription() + " | " + at;
    }
}
