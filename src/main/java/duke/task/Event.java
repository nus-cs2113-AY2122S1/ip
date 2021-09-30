package duke.task;
/**
 * Represents an Event task.
 *
 * <{@link duke.task.Type} enum corresponds to respective class.
 */
class Event extends TimedTask {
    private static final Type type = Type.EVENT;

    /**
     * Event constructor with <code>isDone</code> set to <code>false</code>.
     *
     * @param description Task description.
     * @param dateTime String that describes the dateTime of the task.
     */
    Event(String description, String dateTime) {
        super(description, dateTime, type);
    }

    /**
     * Event constructor
     *
     * @param isDone boolean to show whether task is completed.
     * @param description Task description.
     * @param dateTime String that descripes the dateTime of the task.
     */
    Event(boolean isDone, String description, String dateTime) {
        super(isDone, description, dateTime, type);
    }
}
