package duke;

public class Todo extends Task {

    public Todo(String description) {
        super(description);
    }

    public Todo(String description, boolean isDone) {
        super(description);
        super.setDone(isDone);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
