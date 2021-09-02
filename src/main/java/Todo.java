public class Todo extends Task {

    public Todo(String description, String tasksToDo) {
        super(description);
    }

    public Todo(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return "[T]" + "[" + getStatusIcon() + "]" + " " + description;
    }
}
