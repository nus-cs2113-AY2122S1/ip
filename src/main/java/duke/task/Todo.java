package duke.task;

public class Todo extends Task {

    public Todo(String description) {
        this(description, false);
    }

    public Todo(String description, boolean isDone) {
        super(description, isDone);
    }

    @Override
    public String toString() {
        return String.format("[T][%s] %s", getStatusIcon(), this.description);
    }

    @Override
    public String toSave() {
        return String.format("todo | %s | %b", this.description, this.isDone);
    }
}
