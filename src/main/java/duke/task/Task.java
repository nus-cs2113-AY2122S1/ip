package duke.task;

public abstract class Task {
    private final String name;
    private Boolean isDone;

    /**
     * Task constructor
     *
     * @param name Name of task
     */
    public Task(String name) {
        this.name = name;
        this.isDone = false;
    }

    /**
     * @return Name of task
     */
    public String getName() {
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
    public abstract String getTime();

    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + getName();
    }

    /**
     * @return Time of Task in yyyy-mm-dd format
     */
    public abstract String getStandardTime();
}
