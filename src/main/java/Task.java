public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return isDone ? "X" : " ";
    }

    public void markAsDone() {
        isDone = true;
        System.out.print(Duke.INDENT + "Nice! I've marked this task as done: " +
                Duke.LINE_SEPARATOR + Duke.INDENT + " ");
        System.out.println(this);
    }

    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }

}
