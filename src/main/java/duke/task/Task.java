package duke.task;

public abstract class Task {

    protected String description;
    protected boolean isDone;

    public String getDescription() {
        return description;
    }

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Returns the status icon "X" if task is done and " "
     * if the task is not done.
     *
     * @return Done status of the task.
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    /**
     * Marks the task as done.
     */
    public void markAsDone() {
        isDone = true;
    }

    /**
     * Returns the task as a string to be written in a data file.
     *
     * @return Task in text format e.g. "| 0 | [description]".
     */
    public String toText() {
        return "| " + (isDone ? 1 : 0) + " | " + description;
    }

    /**
     * Returns the task as a string to be displayed in the terminal for the user
     * to read the task in string format.
     *
     * @return Task in string format e.g. "[X] [description]".
     */
    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }
}
