public class Task {
    protected String task;
    protected boolean isDone;

    public Task(String task) {
        this.task = task;
        this.isDone = false;
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public String displayTask() {
        String doneIcon = isDone ? "X" : " ";
        return ("[" + doneIcon + "]" + task);
    }
}
