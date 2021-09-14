package austin;

public class Todo extends Task {
    public Todo(String description) {
        super(description);
    }

    public String toFileFormat() {
        return "T | " + (isDone ? "1" : "0") + " # " + description + "\n";
    }

    @Override
    public String toString() {
        return "[T] [" + getStatus() + "] " + getDescription();
    }
}