import src.main.java.Task;

public class Deadline extends Task {
    protected String by;

    public Deadline(String description, char taskType, String by) {
        super(description, taskType);
        this.by = by;
    }

    @Override
    public String toString() {
        return " [D][" + getStatusIcon() + "] " + super.toString() + by;
    }
}
