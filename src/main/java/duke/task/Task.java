package duke.task;

public abstract class Task {

    protected String taskName;
    protected boolean isDone;

    /**
     * A constructor of a task.
     *
     * @param taskName name of task.
     */
    public Task(String taskName) {
        this.taskName = taskName;
        this.isDone = false;
    }

    /**
     * Check if a task is completed.
     *
     * @return boolean value of task completion status.
     */
    public boolean isDone() {
        return isDone;
    }

    public void markDone() {
        isDone = true;
    }

    /**
     * Returns a String format of task to be printed.
     *
     * @return String of isDone and task name.
     */
    @Override
    public String toString() {
        if (isDone()) {
            return "[✓] " + taskName;
        }
        else {
            return "[ ] " + taskName;
        }
    }

    /**
     * Returns a String format of task to be stored in storage text file.
     *
     * @return String of isDone and task name.
     */
    public String storageText() {
        if (isDone()) {
            return "|1|" + taskName;
        }
        else {
            return "|0|" + taskName;
        }
    }
}
