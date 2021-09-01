public class Todo extends Task {
    public Todo(String description) {
        super(description);
        complete = false;
    }
    public String toString() {
        return "[T]" + super.toString();
    }
}