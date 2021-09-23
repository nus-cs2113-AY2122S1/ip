package duke.task;

public class Task {
    private String description;
    private boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    /**
     * Checks if a task is completed and returns a corresponding status icon.
     * @return "X" if task is done, " " if task is not done.
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    public String getDescription() {
        return description;
    }

    public void setDone() {
        isDone = true;
    }

    /**
     * Getter for task subclasses that obtains the task type in the form of a task type icon.
     * @return 'T' for a todo task, 'D' for a deadline task, 'E' for an event task.
     */
    public String getType() {
        return null;
    }

    public String getByDateTime() {
        return null;
    }

    public String getStartAndEndTime() {
        return null;
    }
}
