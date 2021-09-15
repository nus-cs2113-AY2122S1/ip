package task.type;

public class Deadline extends Task {
    protected String by;
    public static final String type = "D";

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }
    public Deadline(String description, String by, boolean isDone) {
        super(description, isDone);
        this.by = by;
    }

    public String getBy(){
        return by;
    }
    @Override
    public String toString() {
        return "[" + type + "]" + super.toString() + " (by: " + by + ")";
    }

    @Override
    public String getTaskType() {
        return type;
    }
}