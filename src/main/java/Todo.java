public class Todo extends Task {
    public Todo(String description) {
        super(description);
    }

    public String getClassType() {
        return "[T]";
    }

    @Override
    public String toString() {
        return getClassType() + getStatusIcon() + getDescription();
    }
}
