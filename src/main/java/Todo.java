public class Todo extends Task {

    public Todo(String description) {
        super(description);
    }

    @Override
    public String getTaskType() {
        return "T";
    }

    @Override
    public String toString() {
        return super.toString() + " [T][ ] " + description + "\n";
    }

    @Override
    public String getDescription() {
        return description + "\n";
    }

}