package duke.task;

public class Deadline extends Task {

    public static final String OPEN_BRACKET = "[";
    public static final String CLOSE_BRACKET = "]";

    public Deadline(String description, String date) {
        super(description);
        this.date = date;
        this.typeIcon = "D";
    }

    @Override
    public String toString() {
        return OPEN_BRACKET + typeIcon + CLOSE_BRACKET + super.toString() + " (by: " + date + ")";
    }
}
