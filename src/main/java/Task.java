public abstract class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = "[][ ]" + description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    public void setAsDone() {
        this.isDone = true;
        this.description = this.description.replace("[ ]", "[" + this.getStatusIcon()+"]");
    }
}
