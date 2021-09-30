public class Todo extends Task {
    protected boolean isDone;

    public Todo(String description) {
        super(description);
        isDone = false;
    }

    public void setDone(boolean isDone) {
        this.isDone = isDone;
    }

    public boolean isDone() {
        return this.isDone;
    }

    public String toString() {
        return "[T]" + super.toString();
    }
}