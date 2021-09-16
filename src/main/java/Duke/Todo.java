package Duke;

public class Todo extends Task {
    public Todo(String description) {
        super(description);
        taskType = "T";
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
