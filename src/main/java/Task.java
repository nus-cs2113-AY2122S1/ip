public class Task {
    protected String content;
    protected Boolean isDone;

    public Task(String content) {
        this.content = content;
        isDone = false;
    }

    public String TaskStatus() {
        return (isDone? "X":" ");
    }

    public void markAsDone() {
        this.isDone = true;
    }
    @Override
    public String toString() {
        return "[" + this.TaskStatus() + "] " + this.content;
    }
}

