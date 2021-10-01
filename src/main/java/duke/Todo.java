package duke;

public class Todo extends Task {

    private final String taskType = "T";

    public Todo(String description) {
        super(description);
    }

    public Todo(String description, boolean isDone) {
        super(description);
        super.setDone(isDone);
    }

    @Override
    public String taskString() {
        return this.taskType + " | " + super.getStatusIcon() + " | " + super.description + " | ";
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
