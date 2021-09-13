package karlett.task;

public class Deadline extends Task {

    protected String by;

    public Deadline(boolean isProcessingUserInput, String description, String by) {
        this.description = description;
        this.isDone = false;
        this.by = by;
        increaseNumberOfTasks();
        if (isProcessingUserInput) {
            printNewTaskAddedMessage();
        }
    }

    @Override
    public String toString() {
        return "[D] [" + this.getStatusIcon() + "] " + this.getDescription() + " (by: " + by + ")";
    }
}
