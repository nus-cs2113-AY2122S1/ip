public class Todo extends Task {

    protected boolean isDone;

    public Todo(String description) {
        super(description);
    }

    public boolean isDone() {
        return isDone;
    }

    @Override
    public String toString() {
        return "[  To do  ]" + getStatusIcon() + super.toString();
    }
}
