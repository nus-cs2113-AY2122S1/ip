public class Todo extends Task {
    public Todo(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return getClassType() + getStatusIcon() + getDescription();
    }

    public String getClassType() {
        return "[T]";
    }
}
