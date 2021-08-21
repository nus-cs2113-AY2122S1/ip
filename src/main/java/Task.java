public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        if (this.isDone) {
            return "X";
        } else {
            return " ";
        }
    }

    public void markDone() {
        this.isDone = true;
    }
}
