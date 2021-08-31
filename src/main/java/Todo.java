public class Todo extends Task {
    public Todo(String description) {
        super(description);
    }

    public String getTypeIcon() {
        return "T";
    }

    public String toString() {
        return "[T]" + "[" + this.getStatusIcon() + "] " + this.getDescription();
    }
}