package duke.task;

public class Deadline extends Task {
    private final String taskDeadline;

    /**
     * Initializes the task description and deadline, and
     * sets initial status to "not done".
     *
     * @param description Task description.
     * @param taskDeadline Task deadline.
     */
    public Deadline(String description, String taskDeadline) {
        super(description);
        this.taskDeadline = taskDeadline;
    }

    /**
     * Initializes the task description and deadline, and
     * sets initial status according to the given parameter.
     *
     * @param description Task description.
     * @param taskDeadline Task deadline.
     * @param isDone Initial status.
     */
    public Deadline(String description, String taskDeadline, boolean isDone) {
        super(description, isDone);
        this.taskDeadline = taskDeadline;
    }

    public String getTaskDeadline() {
        return taskDeadline;
    }

    @Override
    public String serialize() {
        return DEADLINE + " | " + (isDone ? "1" : "0") + " | " + description + " | " + taskDeadline;
    }

    @Override
    public String toString() {
        return "[" + DEADLINE + "]" + super.toString() + " (by: " + taskDeadline + ")";
    }
}
