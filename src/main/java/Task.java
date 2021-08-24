public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    public String getDescription() {
        return description;
    }

    public void isDone() {
        this.isDone = true;
        System.out.println("\n____________________________________________________________");
        System.out.println("Nice! I've marked this task as done: ");
        System.out.println("  [" + this.getStatusIcon()+ "] " + this.getDescription());
        System.out.println("____________________________________________________________");
    }
}