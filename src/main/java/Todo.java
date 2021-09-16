public class Todo extends Task {
    public Todo(String description) {
        super(description);
        complete = false;
    }

    public String getType() {
        return "T";
    }

    public String toString() {
        return "[T]" + super.toString();
    }
}