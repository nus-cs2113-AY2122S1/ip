package duke.task;

public class Event extends Task {
    private final String taskPeriod;

    /**
     * Initializes the task description and deadline, and
     * sets initial status to "not done".
     *
     * @param description task description
     * @param taskPeriod time period of the task
     */
    public Event(String description, String taskPeriod) {
        super(description);
        this.taskPeriod = taskPeriod;
    }

    /**
     * Initializes the task description and deadline, and
     * sets initial status according to the given parameter.
     *
     * @param description task description
     * @param taskPeriod time period of the task
     * @param isDone initial status
     */
    public Event(String description, String taskPeriod, boolean isDone) {
        super(description, isDone);
        this.taskPeriod = taskPeriod;
    }

    public String getTaskPeriod() {
        return taskPeriod;
    }

    @Override
    public String serialize() {
        return EVENT + " | " + (isDone ? "1" : "0") + " | " + description + " | " + taskPeriod;
    }

    @Override
    public String toString() {
        return "[" + EVENT + "]" + super.toString() + " (at: " + taskPeriod + ")";
    }
}
