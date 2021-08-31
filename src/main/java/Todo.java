public class Todo extends Task {

    public Todo(String description) {
        super(description);
    }

    public String getStatusIcon() {
        return "[T]" + (isDone ? "[X]" : "[ ]"); //return tick or X symbols
    }

    @Override
    public String toString() {
        return this.getStatusIcon() + description;
    }

    public void markAsDone() {
        this.isDone = true;
    }
}