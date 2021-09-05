public class Todo extends Task {
    public Todo(String description) {
        super(description);
        this.type = TaskType.TODO;
    }

    @Override
    public String toString() {
        return String.format("[%s][%s] %s",
                this.type.getTaskLabel(), this.getStatusIcon(), this.description);
    }
}
