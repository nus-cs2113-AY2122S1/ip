public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public void taskDone() {
        this.isDone = true;
    }

    public String getName() {
        return this.description;
    }
}
