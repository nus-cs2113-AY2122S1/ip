public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        if (isDone) {
            return "X";
        }
        return " ";
    }
    public void markAsDone() {
        isDone = true;
        System.out.println(" Nice! I've marked this task as done: ");
        System.out.println("  [X] " + description);
    }

}
