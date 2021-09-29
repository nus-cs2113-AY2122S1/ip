package Task;

public class ToDo extends Task {
    protected boolean isDone;

    public ToDo(String description) {
        super(description);
        isDone = false;
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
