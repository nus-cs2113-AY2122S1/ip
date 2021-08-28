public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String task) {
        this.description = task;
        this.isDone = false;
    }

    public void markAsDone() {
        this.isDone = true;
    }

    @Override
    public String toString() {
        String doneIcon = isDone ? "X" : " ";
        return "[" + doneIcon + "]" + description;
    }
}
