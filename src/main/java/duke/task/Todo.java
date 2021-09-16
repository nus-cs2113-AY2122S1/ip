package duke.task;

public class Todo extends Task {
    public Todo(String description) {
        super(description);
    }

    public String getCode() {
        return "T";
    }

    @Override
    public String toString() {
        return "[T][" + super.getStatusIcon() + "] " + super.getDescription();
    }
}
