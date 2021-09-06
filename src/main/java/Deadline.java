public class Deadline extends Task {

    protected String by;

    public Deadline(String description, String by) {
        this.description = description;
        this.isDone = false;
        this.by = by;
        increaseNumberOfTasks();
        printNewTaskAddedMessage();
    }

    @Override
    public String toString() {
        return "[D] [" + this.getStatusIcon() + "] " + this.getDescription() + " (by: " + by + ")";
    }
}
