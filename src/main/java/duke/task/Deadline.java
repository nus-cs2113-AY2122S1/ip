package duke.task;

public class Deadline extends Task {
    private final String taskDeadline;

    /**
     * Initializes the task description and deadline, and
     * sets initial status to "not done".
     *
     * @param description task description
     * @param taskDeadline task deadline
     */
    public Deadline(String description, String taskDeadline) {
        super(description);
        this.taskDeadline = taskDeadline;
    }

    /**
     * Initializes the task description and deadline, and
     * sets initial status according to the given parameter.
     *
     * @param description task description
     * @param taskDeadline task deadline
     * @param isDone initial status
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
