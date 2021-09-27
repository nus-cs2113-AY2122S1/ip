package duke.task;
/**
 * Represents a Deadline task.
 * <code>Type</code> enum corresponds to respective class.
 */
class Deadline extends TimedTask {
    private static final Type type = Type.DEADLINE;

    /**
     * Deadline constructor with <code>isDone</code> set to <code>false</code>.
     *
     * @param description Task description.
     * @param dateTime String that describes the dateTime of the task.
     */
    Deadline(String description, String dateTime) {
        super(description, dateTime, type);
    }

    /**
     * Deadline constructor
     *
     * @param isDone boolean to show whether task is completed.
     * @param description Task description.
     * @param dateTime String that descripes the dateTime of the task.
     */
    Deadline(boolean isDone, String description, String dateTime) {
        super(isDone, description, dateTime, type);
    }
}
