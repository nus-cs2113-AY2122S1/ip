package duke.task;

public abstract class Task {
    private final String name;
    private Boolean isDone;

    /**
     * Task constructor
     *
     * @param title Title of task
     */
    public Task(String title) {
        this.name = title;
        this.isDone = false;
    }

    /**
     * @return Title of task
     */
    public String getTitle() {
        return name;
    }

    /**
     * Returns an icon for task completion status
     *
     * @return Status icon of task
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    /**
     * Marks task as done
     */
    public void markAsDone() {
        isDone = true;
    }

    /**
     * @return Type of Task
     */
    public abstract TaskType getTaskType();

    /**
     * @return Time of Task in MMM dd yyyy format
     */
    public abstract String getDisplayTime();

    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + getTitle();
    }

    /**
     * @return Time of Task in yyyy-mm-dd format
     */
    public abstract String getStandardTime();
}
