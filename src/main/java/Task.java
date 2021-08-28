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

    public String displayTask() {
        String doneIcon = isDone ? "X" : " ";
        return ("[" + doneIcon + "]" + description);
    }

    @Override
    public String toString() {
        String doneIcon = isDone ? "X" : " ";
        return "[" + doneIcon + "]" + description;
    }
}
