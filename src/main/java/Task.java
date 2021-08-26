public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getDescription(){
        return this.description;
    }
    public String getStatusIcon() {
        return (isDone ? "[X] " : "[ ] "); // mark done task with X
    }

    public void markDone(){
        if (!this.isDone) {
            this.isDone = true;
            System.out.println("Nice! I've marked this task as done:");
        }
    }
}
