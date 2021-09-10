package duke.task;

public class Event extends Task {

    public static final String OPEN_BRACKET = "[";
    public static final String CLOSE_BRACKET = "]";

    public Event(String description, String date) {
        super(description);
        this.date = date;
        this.typeIcon = "E";
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return OPEN_BRACKET + typeIcon + CLOSE_BRACKET + super.toString() + " (at: " + date + ")";
    }
}
