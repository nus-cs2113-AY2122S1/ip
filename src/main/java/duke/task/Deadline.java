package duke.task;

public class Deadline extends Task {

    protected String by;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    public Deadline(int status, String description, String by) {
        super(status, description);
        this.by = by;
    }

    @Override
    public String getType() {
        return "deadline";
    }

    @Override
    public String getTime() {
        return this.by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + "(by: " + by + ")";
    }
}
