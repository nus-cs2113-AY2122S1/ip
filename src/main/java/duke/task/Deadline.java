package duke.task;

public class Deadline extends Task {

    private String by;

    public Deadline(String description, String by) {
        this(description, by, false);
    }

    public Deadline(String description, String by, boolean isDone) {
        super(description, isDone);
        this.by = by;
    }

    @Override
    public String toString() {
        return String.format("[D][%s] %s (by: %s)", getStatusIcon(), this.description, this.by);
    }

    @Override
    public String toSave() {
        return String.format("deadline | %s | %b | %s", this.description, this.isDone, this.by);
    }
}
